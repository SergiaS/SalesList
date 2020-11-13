package org.mySells.web;

import org.mySells.enums.DeliveryServiceEnum;
import org.mySells.enums.MarketPlaceEnum;
import org.mySells.enums.OrderStatusEnum;
import org.mySells.enums.PaymentMethodEnum;
import org.mySells.model.Product;
import org.mySells.web.product.ProductRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static org.mySells.util.DateTimeUtil.parseLocalDate;
import static org.mySells.util.DateTimeUtil.parseLocalTime;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(ProductServlet.class);

	private ConfigurableApplicationContext springContext;
	private ProductRestController productController;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
		productController = springContext.getBean(ProductRestController.class);
	}

	@Override
	public void destroy() {
		springContext.close();
		super.destroy();
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
				new BigDecimal(request.getParameter("price").replace(",", ".")),
				new BigDecimal(request.getParameter("spent").replace(",", ".")),
				Integer.parseInt(request.getParameter("payoutPercentage")),
				request.getParameter("notes").trim()
		);

		if (StringUtils.isEmpty(request.getParameter("id"))) {
			productController.create(product);
		} else {
			productController.update(product, getId(request));
		}

		response.sendRedirect("products");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		logger.info("action={}, path={}", action , request.getServletPath());

		switch (action == null ? "all" : action) {
			case "delete" -> {
				productController.delete(getId(request));
				response.sendRedirect("products");
			}
			case "create", "update" -> {
				final Product product = "create".equals(action) ? getDefaultProduct() : productController.get(getId(request));
				request.setAttribute("product", product);

				request.setAttribute("marketPlace", new ArrayList<>(Arrays.asList(MarketPlaceEnum.values())));
				request.setAttribute("deliveryService", new ArrayList<>(Arrays.asList(DeliveryServiceEnum.values())));
				request.setAttribute("paymentMethod", new ArrayList<>(Arrays.asList(PaymentMethodEnum.values())));
				request.setAttribute("orderStatus", new ArrayList<>(Arrays.asList(OrderStatusEnum.values())));
				request.getRequestDispatcher("/product-form.jsp").forward(request, response);
			}
			case "filter" -> {
				LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
				LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
				LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
				LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
				request.setAttribute("products", productController.getBetween(startDate, startTime, endDate, endTime));
				request.getRequestDispatcher("/products.jsp").forward(request, response);
			}
			default -> {
				request.setAttribute("products", productController.getAll());
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
				new BigDecimal("0"),
				new BigDecimal("0"),
				0,
				""
		);
	}
}
