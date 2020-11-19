package org.mySells.service;

import org.mySells.model.Product;
import org.mySells.repository.ProductRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static org.mySells.util.DateTimeUtil.atStartOfDayOrMin;
import static org.mySells.util.DateTimeUtil.atStartOfNextDayOrMax;
import static org.mySells.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public List<Product> getBetweenInclusive(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId) {
        return repository.getBetweenHalfOpen(atStartOfDayOrMin(startDate), atStartOfNextDayOrMax(endDate), userId);
    }

    public List<Product> getAll(int userId) {
        return repository.getAll(userId);
    }

    public void update(Product product, int userId) {
        Assert.notNull(product, "product must not be null");
        checkNotFoundWithId(repository.save(product, userId), product.id());
    }

    public Product create(Product product, int userId) {
        Assert.notNull(product, "product must not be null");
        return repository.save(product, userId);
    }
}
