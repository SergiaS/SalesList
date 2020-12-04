package org.saleslist.util;

import org.saleslist.model.Product;
import org.saleslist.to.ProductTo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductsUtil {
    public static final int DEFAULT_PROFIT_PER_DAY = 500;

    public ProductsUtil() {
    }

    public static List<ProductTo> getTos(Collection<Product> products, int profitedPerDay) {
        return filteredByPredicate(products, profitedPerDay, product -> true);
    }

    public static List<ProductTo> getFilteredTos(Collection<Product> products, int profitedPerDay, LocalDate startDate, LocalDate endDate) {
        return filteredByPredicate(products, profitedPerDay, product -> Util.isBetweenDateOrTime(product.getDate(), startDate, endDate));
    }

    public static List<ProductTo> filteredByPredicate(Collection<Product> products, int profitedPerDay, Predicate<Product> filter) {
        Map<LocalDate, BigDecimal> profitedSumByDate = products.stream()
                .collect(Collectors.toMap(Product::getDate, Product::getProfit, BigDecimal::add));

        return products.stream()
                .filter(filter)
                .map(product -> createTo(product, profitedSumByDate.get(product.getDate()).compareTo(BigDecimal.valueOf(profitedPerDay)) > 0))
                .collect(Collectors.toList());
    }

    private static ProductTo createTo(Product product, boolean profited) {
        return new ProductTo(
                product.getId(),
                product.getDateTime(),
                product.getTitle(),
                product.getMarketPlace(),
                product.getDeliveryService(),
                product.getPaymentMethod(),
                product.getOrderStatus(),
                product.getSoldAtPrice(),
                product.getSpent(),
                product.getPayoutPercentage(),
                product.getNotes(),
                profited
        );
    }


}
