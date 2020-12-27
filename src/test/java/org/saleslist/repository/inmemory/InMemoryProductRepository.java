package org.saleslist.repository.inmemory;

import org.saleslist.ProductTestData;
import org.saleslist.UserTestData;
import org.saleslist.model.Product;
import org.saleslist.repository.ProductRepository;
import org.saleslist.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryProductRepository.class);

    // Map userId -> productRepository
    private final Map<Integer, InMemoryBaseRepository<Product>> usersProductsMap = new ConcurrentHashMap<>();

    {
        var userProducts = new InMemoryBaseRepository<Product>();
        ProductTestData.PRODUCTS.forEach(meal -> userProducts.map.put(meal.getId(), meal));
        usersProductsMap.put(UserTestData.USER_ID, userProducts);
    }

    @Override
    public Product save(Product meal, int userId) {
        var products = usersProductsMap.computeIfAbsent(userId, uid -> new InMemoryBaseRepository<>());
        return products.save(meal);
    }

    @PostConstruct
    public void postConstruct() {
        log.info("+++ PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("+++ PreDestroy");
    }

    @Override
    public boolean delete(int id, int userId) {
        var products = usersProductsMap.get(userId);
        return products != null && products.delete(id);
    }

    @Override
    public Product get(int id, int userId) {
        var products = usersProductsMap.get(userId);
        return products == null ? null : products.get(id);
    }

    @Override
    public List<Product> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return filterByPredicate(userId, products -> Util.isBetween(products.getDateTime(), startDateTime, endDateTime));
    }

    @Override
    public List<Product> getAll(int userId) {
        return filterByPredicate(userId, products -> true);
    }

    private List<Product> filterByPredicate(int userId, Predicate<Product> filter) {
        var products = usersProductsMap.get(userId);
        return products == null ? Collections.emptyList() :
                products.getCollection().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Product::getDateTime).reversed())
                        .collect(Collectors.toList());
    }

    // don't need to test it
    @Override
    public List<String> getOwnersNames() {
        return null;
    }
}
