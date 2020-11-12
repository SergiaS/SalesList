package org.saleslist.repository.inmemory;

import org.saleslist.enums.DeliveryServiceEnum;
import org.saleslist.enums.MarketPlaceEnum;
import org.saleslist.enums.OrderStatusEnum;
import org.saleslist.enums.PaymentMethodEnum;
import org.saleslist.model.Product;
import org.saleslist.repository.ProductRepository;
import org.saleslist.util.ProductsUtil;
import org.saleslist.util.Util;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.saleslist.repository.inmemory.InMemoryUserRepository.ADMIN_ID;
import static org.saleslist.repository.inmemory.InMemoryUserRepository.USER_ID;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private final Map<Integer, InMemoryBaseRepository<Product>> usersProductsMap = new ConcurrentHashMap<>();
    {
        ProductsUtil.PRODUCTS.forEach(product -> save(product, USER_ID));
        save(new Product(LocalDateTime.of(2020, Month.NOVEMBER, 3, 9, 33), "Tyres", MarketPlaceEnum.SITE, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.SAVE_SERVICE, OrderStatusEnum.SUCCESS, new BigDecimal("2700"), new BigDecimal("2400"), 0, ""), ADMIN_ID);
        save(new Product(LocalDateTime.of(2020, Month.NOVEMBER, 3, 15, 18), "Light", MarketPlaceEnum.SITE, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.SAVE_SERVICE, OrderStatusEnum.SUCCESS, new BigDecimal("1800"), new BigDecimal("1250"), 0, ""), ADMIN_ID);
        save(new Product(LocalDateTime.of(2020, Month.NOVEMBER, 3, 23, 45), "Oil", MarketPlaceEnum.SITE, DeliveryServiceEnum.JUSTIN, PaymentMethodEnum.CASH_ON_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("285"), new BigDecimal("180"), 0, ""), ADMIN_ID);
    }

    @Override
    public Product save(Product product, int userId) {
        InMemoryBaseRepository<Product> products = usersProductsMap.computeIfAbsent(userId, uid -> new InMemoryBaseRepository<>());
        return products.save(product);
    }

    @Override
    public boolean delete(int id, int userId) {
        InMemoryBaseRepository<Product> products = usersProductsMap.get(userId);
        return products != null && products.delete(id);
    }

    @Override
    public Product get(int id, int userId) {
        InMemoryBaseRepository<Product> products = usersProductsMap.get(userId);
        return products == null ? null : products.get(id);
    }

    @Override
    public List<Product> getAll(int userId) {
        return filteredByPredicate(userId, product -> true);
    }

    @Override
    public List<Product> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return filteredByPredicate(userId, product -> Util.isBetweenHalfOpen(product.getDateTime(), startDateTime, endDateTime));
    }

    private List<Product> filteredByPredicate(int userId, Predicate<Product> filter) {
        InMemoryBaseRepository<Product> products = usersProductsMap.get(userId);
        return products == null ? Collections.emptyList() :
                products.getCollection().stream()
                .filter(filter)
                .sorted(Comparator.comparing(Product::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}