package org.saleslist.repository;

import org.saleslist.model.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository {

    Product save(Product product, int userId);

    boolean delete(int id, int userId);

    Product get(int id, int userId);

    List<Product> getAll(int userId);

    List<Product> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    default Product getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }

    List<String> getOwnersNames();
}
