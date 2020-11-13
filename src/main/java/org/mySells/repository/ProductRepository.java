package org.mySells.repository;

import org.mySells.model.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository {
    // null if updated meal do not belong to userId
    Product save(Product product, int userId);

    // false if meal do not belong to userId
    boolean delete(int id, int userId);

    // null if meal do not belong to userId
    Product get(int id, int userId);

    // ORDERED dateTime desc
    List<Product> getAll(int userId);

    // ORDERED dateTime desc
    List<Product> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);
}
