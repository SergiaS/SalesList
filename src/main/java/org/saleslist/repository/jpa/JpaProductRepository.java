package org.saleslist.repository.jpa;

import org.saleslist.model.Product;
import org.saleslist.repository.MainRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JpaProductRepository implements MainRepository<Product> {

    @Override
    public Product save(Product model, int userId) {
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
    public List<Product> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return null;
    }
}
