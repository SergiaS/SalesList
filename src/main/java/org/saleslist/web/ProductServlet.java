package org.saleslist.web;

import org.saleslist.Profiles;
import org.saleslist.enums.DeliveryServiceEnum;
import org.saleslist.enums.MarketPlaceEnum;
import org.saleslist.enums.OrderStatusEnum;
import org.saleslist.enums.PaymentMethodEnum;
import org.saleslist.model.Product;
import org.saleslist.repository.ProductRepository;
import org.saleslist.web.controller.PayoutRestController;
import org.saleslist.web.controller.ProductRestController;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

import static org.saleslist.util.DateTimeUtil.parseLocalDate;
import static org.saleslist.util.DateTimeUtil.parseLocalTime;
import static org.saleslist.web.SecurityUtil.ADMIN_ID;
import static org.saleslist.web.SecurityUtil.getAuthUserId;

@WebServlet("/products")
public class ProductServlet extends MainServlet<Product> {

    private ClassPathXmlApplicationContext springContext;
    private ProductRestController productController;
    private PayoutRestController payoutController;
    private ProductRepository productRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext(new String[]{"spring/spring-app.xml", "spring/spring-db.xml"}, false);
        springContext.getEnvironment().setActiveProfiles(Profiles.getActiveDbProfile(), Profiles.REPOSITORY_IMPLEMENTATION);
        springContext.refresh();

        productController = springContext.getBean(ProductRestController.class);
        payoutController = springContext.getBean(PayoutRestController.class);
        productRepository = springContext.getBean(ProductRepository.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Product product = fillModel(request);

        if (StringUtils.isEmpty(request.getParameter("id"))) {
            productController.create(product);
        } else {
            productController.update(product, getId(request));
        }

        response.sendRedirect(getTableName());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete" -> {
                int id = getId(request);
                payoutController.delete(id);
                productController.delete(id);
                response.sendRedirect("products");
            }
            case "create", "update" -> {
                final Product product = "create".equals(action) ?
                        getDefaultProduct() : productController.get(getId(request));
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

                if (getAuthUserId() == ADMIN_ID) {
                    request.setAttribute("owners", productRepository.getOwnersNames());
                }

                request.setAttribute("userId", getAuthUserId());
                request.setAttribute("products", productController.getBetween(startDate, startTime, endDate, endTime));
                request.getRequestDispatcher("/products.jsp").forward(request, response);
            }
            default -> {
                if (getAuthUserId() == ADMIN_ID) {
                    request.setAttribute("owners", productRepository.getOwnersNames());
                }

                request.setAttribute("userId", getAuthUserId());
                request.setAttribute("products", productController.getAll());
                request.getRequestDispatcher("/products.jsp").forward(request, response);
            }
        }
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

    @Override
    protected Product fillModel(HttpServletRequest request) {
        return new Product(
                LocalDateTime.parse(request.getParameter("dateTime")).truncatedTo(ChronoUnit.MINUTES),
                request.getParameter("title").trim(),
                MarketPlaceEnum.valueOf(request.getParameter("marketPlace")),
                DeliveryServiceEnum.valueOf(request.getParameter("deliveryService")),
                PaymentMethodEnum.valueOf(request.getParameter("paymentMethod")),
                OrderStatusEnum.valueOf(request.getParameter("orderStatus")),
                new BigDecimal(request.getParameter("soldAtPrice").replace(",", ".")),
                new BigDecimal(request.getParameter("spent").replace(",", ".")),
                Integer.parseInt(request.getParameter("payoutPercentage")),
                request.getParameter("notes").trim());
    }

    @Override
    protected String getTableName() {
        return "products";
    }
}
