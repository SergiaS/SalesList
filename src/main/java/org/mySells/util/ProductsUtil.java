package org.mySells.util;

import org.mySells.model.Product;
import org.mySells.to.ProductTo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductsUtil {

    public static final int DEFAULT_PROFIT_PER_DAY = 500;

    public ProductsUtil() {
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

        return products.stream()
                .filter(filter)
                .map(product -> createTo(product, profitsSumByDate.get(product.getDate()).compareTo(BigDecimal.valueOf(profitsPerDay)) >= 0))
                .collect(Collectors.toList());
    }

    private static ProductTo createTo(Product product, boolean excess) {
        return new ProductTo(product.getId(), product.getDateTime(), product.getTitle(), product.getMarketPlace(), product.getDeliveryService(), product.getPaymentMethod(), product.getOrderStatus(), product.getSoldAtPrice(), product.getSpent(), product.getPayoutPercentage(), product.getNotes(), excess);
    }
}
