package org.saleslist.util;

import org.saleslist.enums.DeliveryServiceEnum;
import org.saleslist.enums.MarketPlaceEnum;
import org.saleslist.enums.OrderStatusEnum;
import org.saleslist.enums.PaymentMethodEnum;
import org.saleslist.model.Product;
import org.saleslist.to.ProductTo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductsUtil {

    public static final int PROFIT_PER_DAY = 500;

    public static final List<Product> PRODUCTS = Arrays.asList(
            new Product(LocalDateTime.of(2020, Month.OCTOBER, 31, 8, 18), "Glasses", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("100"), new BigDecimal("0.0"), 40, ""),
            new Product(LocalDateTime.of(2020, Month.OCTOBER, 31, 10, 43), "Chain", MarketPlaceEnum.FACEBOOK, DeliveryServiceEnum.JUSTIN, PaymentMethodEnum.COMPLETE_PREPAYMENT, OrderStatusEnum.SUCCESS, new BigDecimal("350"), new BigDecimal("150.0"), 0, ""),
            new Product(LocalDateTime.of(2020, Month.OCTOBER, 31, 13, 24), "Glasses", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("400"), new BigDecimal("0.0"), 40, ""),
            new Product(LocalDateTime.of(2020, Month.NOVEMBER, 1, 7, 51), "Glasses", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("100"), new BigDecimal("0.0"), 40, ""),
            new Product(LocalDateTime.of(2020, Month.NOVEMBER, 1, 9, 17), "Bottles", MarketPlaceEnum.SITE, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.SAVE_SERVICE, OrderStatusEnum.SUCCESS, new BigDecimal("600"), new BigDecimal("270"), 0, ""),
            new Product(LocalDateTime.of(2020, Month.NOVEMBER, 1, 14, 30), "Glasses", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("100"), new BigDecimal("0.0"), 40, "")
    );

    public static void main(String[] args) {
        List<ProductTo> tos = getTos(PRODUCTS, PROFIT_PER_DAY);
        tos.forEach(System.out::println);
    }

    public static List<ProductTo> getTos(Collection<Product> products, int profitPerDay) {
        return filteredByPredicate(products, profitPerDay, product -> true);
    }

    public static List<ProductTo> getFilteredTos(Collection<Product> products, int profitsPerDay, LocalTime startTime, LocalTime endTime) {
        return filteredByPredicate(products, profitsPerDay, product -> Util.isBetweenHalfOpen(product.getTime(), startTime, endTime));
    }

    public static List<ProductTo> filteredByPredicate(Collection<Product> products, int profitsPerDay, Predicate<Product> filter) {
        Map<LocalDate, BigDecimal> profitsSumByDate = products.stream()
                .collect(Collectors.toMap(Product::getDate, Product::getProfit, BigDecimal::add));

//        System.out.println(profitsSumByDate);

        return products.stream()
                .filter(filter)
                .map(product -> createTo(product, profitsSumByDate.get(product.getDate()).compareTo(BigDecimal.valueOf(profitsPerDay)) >= 0))
                .collect(Collectors.toList());
    }

    private static ProductTo createTo(Product product, boolean excess) {
        return new ProductTo(product.getId(), product.getDateTime(), product.getTitle(), product.getMarketPlace(), product.getDeliveryService(), product.getPaymentMethod(), product.getOrderStatus(), product.getSoldAtPrice(), product.getSpent(), product.getPayoutPercentage(), product.getNotes(), excess);
    }
}
