package org.saleslist.jdbc.servlets;

import org.saleslist.jdbc.enums.DeliveryServiceEnum;
import org.saleslist.jdbc.enums.MarketPlaceEnum;
import org.saleslist.jdbc.enums.OrderStatusEnum;
import org.saleslist.jdbc.enums.PaymentMethodEnum;
import org.saleslist.jdbc.model.Product;
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

	private JdbcProductRepository repository;

	@Override
	public void init() throws ServletException {
		repository = new JdbcProductRepository();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		double spent = Double.parseDouble(request.getParameter("spent").replace(",", "."));
		double price = Double.parseDouble(request.getParameter("price").replace(",", "."));
		int payoutPercentage = Integer.parseInt(request.getParameter("payout"));
		double profit = profitCalculator(spent, price, payoutPercentage);

		Product product = new Product(
				LocalDateTime.parse(request.getParameter("dateTime")),
				request.getParameter("title").trim(),
				MarketPlaceEnum.valueOf(request.getParameter("marketPlace")),
				DeliveryServiceEnum.valueOf(request.getParameter("deliveryService")),
				PaymentMethodEnum.valueOf(request.getParameter("paymentMethod")),
				OrderStatusEnum.valueOf(request.getParameter("orderStatus")),
				spent,
				price,
				payoutPercentage,
				profit,
				request.getParameter("notes").trim()
		);

		int productId = getId(request);
		logger.info("doPost: productId = {}, product = {}", productId, product);

		if (request.getParameter("id").equals("0")) {
			repository.save(product);
		} else {
			repository.update(productId, product);
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
				int id = getId(request);
				repository.delete(id);
				response.sendRedirect("products");
			}
			case "create", "update" -> {
				Product product = "create".equals(action) ? getDefaultProduct() : repository.getProductById(getId(request));
				request.setAttribute("product", product);

				request.setAttribute("marketPlace", new ArrayList<>(Arrays.asList(MarketPlaceEnum.values())));
				request.setAttribute("deliveryService", new ArrayList<>(Arrays.asList(DeliveryServiceEnum.values())));
				request.setAttribute("paymentMethod", new ArrayList<>(Arrays.asList(PaymentMethodEnum.values())));
				request.setAttribute("orderStatus", new ArrayList<>(Arrays.asList(OrderStatusEnum.values())));
				request.getRequestDispatcher("/product-form.jsp").forward(request, response);
			}
			default -> {
				request.setAttribute("products", repository.getAllProducts());
				request.getRequestDispatcher("/products.jsp").forward(request, response);
			}
		}
	}

	private int getId(HttpServletRequest request) {
		String paramId = Objects.requireNonNull(request.getParameter("id"));
		return Integer.parseInt(paramId);
	}

	private double profitCalculator(double spent, double price, int payoutPercentage) {
		if (payoutPercentage == 0) return price - spent;
		else if (spent == 0) return price - price * (payoutPercentage / 100.0);
		return price - spent - (price / payoutPercentage);
	}

	private Product getDefaultProduct() {
		return new Product(
				LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
				"New product",
				null,
				null,
				null,
				null,
				0.0,
				0.0,
				0,
				""
		);
	}
}
