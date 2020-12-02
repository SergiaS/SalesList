package org.saleslist.web;

import org.saleslist.enums.DeliveryServiceEnum;
import org.saleslist.enums.MarketPlaceEnum;
import org.saleslist.enums.OrderStatusEnum;
import org.saleslist.enums.PaymentMethodEnum;
import org.saleslist.model.Product;
import org.saleslist.repository.jdbc.JdbcProductRepository;
import org.saleslist.web.user.MainServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

import static org.saleslist.web.SecurityUtil.ADMIN_ID;
import static org.saleslist.web.SecurityUtil.getAuthUserId;

@WebServlet("/products")
public class ProductServlet extends MainServlet<Product> {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = springContext.getBean(JdbcProductRepository.class);
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
////		if (product.getPayoutPercentage() > 0) {
////			Payout payout = new Payout();
////			if (productId != 0) {
////				payout.setUserId(productId);
////			} else {
////				payout.setUserId(payoutRepository.getLastProductIdFromDb());
////			}
////			payout.setDateTime(product.getDateTime());
////			payout.setAmount(-product.getPayoutCurrency());
////			payout.setNotes("За товар:\n" + product.getTitle());
////
////			System.out.println("record to PAYOUT db");
////			payoutRepository.addOrUpdate(payout);
////		} else {
////			System.out.println("delete from PAYOUT db");
////			payoutRepository.delete("product_id", productId);
////		}
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.doGet(request, response);
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete" -> {
                repository.delete(getId(request), getAuthUserId());
                response.sendRedirect("products");
            }
            case "create", "update" -> {
                final Product product = "create".equals(action) ?
                        getDefaultProduct() : repository.get(getId(request), getAuthUserId());
                request.setAttribute("product", product);

                request.setAttribute("marketPlace", new ArrayList<>(Arrays.asList(MarketPlaceEnum.values())));
                request.setAttribute("deliveryService", new ArrayList<>(Arrays.asList(DeliveryServiceEnum.values())));
                request.setAttribute("paymentMethod", new ArrayList<>(Arrays.asList(PaymentMethodEnum.values())));
                request.setAttribute("orderStatus", new ArrayList<>(Arrays.asList(OrderStatusEnum.values())));

                request.getRequestDispatcher("/product-form.jsp").forward(request, response);
            }
            default -> {
                if (getAuthUserId() == ADMIN_ID) {
                    request.setAttribute("owners", repository.getOwnersNames());
                }

                request.setAttribute("userId", getAuthUserId());
                request.setAttribute("products", repository.getAll(getAuthUserId()));
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
                LocalDateTime.parse(request.getParameter("dateTime")),
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
