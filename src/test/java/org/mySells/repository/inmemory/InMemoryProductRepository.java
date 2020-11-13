package org.mySells.repository.inmemory;

import org.mySells.enums.DeliveryServiceEnum;
import org.mySells.enums.MarketPlaceEnum;
import org.mySells.enums.OrderStatusEnum;
import org.mySells.enums.PaymentMethodEnum;
import org.mySells.model.Product;
import org.mySells.repository.ProductRepository;
import org.mySells.util.ProductsUtil;
import org.mySells.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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

import static org.mySells.UserTestData.ADMIN_ID;
import static org.mySells.UserTestData.USER_ID;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryProductRepository.class);

    // Map userId -> productRepository
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

    @PostConstruct
    public void postConstract() {
        log.info("+++ PostConstract");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("+++ PreDestroy");
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
