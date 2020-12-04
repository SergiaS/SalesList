package org.saleslist.service;

import org.saleslist.model.Product;
import org.saleslist.repository.jdbc.JdbcMainRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static org.saleslist.util.DateTimeUtil.atStartOfDayOrMin;
import static org.saleslist.util.DateTimeUtil.atStartOfNextDayOrMax;
import static org.saleslist.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ProductService {

    private final JdbcMainRepository<Product> productRepository;

    public ProductService(JdbcMainRepository<Product> productRepository) {
        this.productRepository = productRepository;
    }

    public Product get(int id, int userId) {
        return checkNotFoundWithId(productRepository.get(id, userId), id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(productRepository.delete(id, userId), id);
    }

    public List<Product> getBetweenInclusive(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId) {
        return productRepository.getBetween(atStartOfDayOrMin(startDate), atStartOfNextDayOrMax(endDate), userId);
    }

    public List<Product> getAll(int userId) {
        return productRepository.getAll(userId);
    }

    public void update(Product product, int userId) {
        Assert.notNull(product, "product must be not null");
        productRepository.save(product, userId);
    }

    public Product create(Product product, int userId) {
        Assert.notNull(product, "product must be not null");
        return productRepository.save(product, userId);
    }
}
