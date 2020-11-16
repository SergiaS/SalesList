package org.mySells.repository.jdbc;

import org.mySells.model.Product;
import org.mySells.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcProductRepository implements ProductRepository {

    @Override
    public Product save(Product product, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Product get(int id, int userId) {
        return null;
    }

    @Override
    public List<Product> getAll(int userId) {
        return null;
    }

    @Override
    public List<Product> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return null;
    }
}
