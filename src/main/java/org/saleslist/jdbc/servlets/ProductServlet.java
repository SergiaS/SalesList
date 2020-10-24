package org.saleslist.jdbc.servlets;

import org.saleslist.jdbc.enums.DeliveryServiceEnum;
import org.saleslist.jdbc.enums.MarketPlaceEnum;
import org.saleslist.jdbc.enums.OrderStatusEnum;
import org.saleslist.jdbc.enums.PaymentMethodEnum;
import org.saleslist.jdbc.model.Payout;
import org.saleslist.jdbc.model.Product;
import org.saleslist.jdbc.repository.JdbcPayoutRepository;
import org.saleslist.jdbc.repository.JdbcProductRepository;
import org.saleslist.jdbc.util.Stats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(ProductServlet.class);

	private JdbcProductRepository productRepository;
	private JdbcPayoutRepository payoutRepository;

	@Override
	public void init() throws ServletException {
		productRepository = new JdbcProductRepository();
		payoutRepository = new JdbcPayoutRepository();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Product product = new Product(
				LocalDateTime.parse(request.getParameter("dateTime")),
				request.getParameter("title").trim(),
				MarketPlaceEnum.valueOf(request.getParameter("marketPlace")),
				DeliveryServiceEnum.valueOf(request.getParameter("deliveryService")),
				PaymentMethodEnum.valueOf(request.getParameter("paymentMethod")),
				OrderStatusEnum.valueOf(request.getParameter("orderStatus")),
				Double.parseDouble(request.getParameter("spent").replace(",", ".")),
				Double.parseDouble(request.getParameter("price").replace(",", ".")),
				Integer.parseInt(request.getParameter("payoutPercentage")),
				request.getParameter("notes").trim(),
				request.getParameter("payoutPaid") == null ? false : true
		);
		product.setPayoutCurrency(product.payoutCurrencyCalculation());
		product.setProfit(product.profitCalculation());

		int productId = getId(request);
		logger.info("doPost: productId = {}, product = {}", productId, product);

		if (request.getParameter("id").equals("0")) {
			productRepository.save(product);
		} else {
			productRepository.update(productId, product);
		}

		if (product.getPayoutPercentage() > 0) {
			Payout payout = new Payout();
			if (productId != 0) {
				payout.setProductId(productId);
			} else {
				payout.setProductId(payoutRepository.getLastProductIdFromDb());
			}
			payout.setDateTime(product.getDateTime());
			payout.setAmount(-product.getPayoutCurrency());
			payout.setNotes("За товар:\n" + product.getTitle());

			System.out.println("record to PAYOUT db");
			payoutRepository.addOrUpdate(payout);
		} else {
			System.out.println("delete from PAYOUT db");
			payoutRepository.delete("product_id", productId);
		}

		response.sendRedirect("products");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		logger.info("doGet: PATH = {}, ACTION = {}", request.getServletPath(), action);

		Stats statistics = new Stats();
		request.setAttribute("stats", statistics);

		switch (action == null ? "all" : action) {
			case "delete" -> {
				productRepository.delete(getId(request));
				response.sendRedirect("products");
			}
			case "create", "update" -> {
				Product product = "create".equals(action) ? getDefaultProduct() : productRepository.getProductById(getId(request));
				request.setAttribute("product", product);

				request.setAttribute("marketPlace", new ArrayList<>(Arrays.asList(MarketPlaceEnum.values())));
				request.setAttribute("deliveryService", new ArrayList<>(Arrays.asList(DeliveryServiceEnum.values())));
				request.setAttribute("paymentMethod", new ArrayList<>(Arrays.asList(PaymentMethodEnum.values())));
				request.setAttribute("orderStatus", new ArrayList<>(Arrays.asList(OrderStatusEnum.values())));
				request.getRequestDispatcher("/product-form.jsp").forward(request, response);
			}
			default -> {
				request.setAttribute("products", productRepository.getAllProducts());
				request.getRequestDispatcher("/products.jsp").forward(request, response);
			}
		}
	}

	private int getId(HttpServletRequest request) {
		String paramId = Objects.requireNonNull(request.getParameter("id"));
		return Integer.parseInt(paramId);
	}

	private Product getDefaultProduct() {
		return new Product(
				LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
				"New product",
				MarketPlaceEnum.OLX,
				DeliveryServiceEnum.NOVA_POST,
				PaymentMethodEnum.OLX_DELIVERY,
				OrderStatusEnum.SUCCESS,
				0,
				0,
				0,
				"",
				false
		);
	}
}
