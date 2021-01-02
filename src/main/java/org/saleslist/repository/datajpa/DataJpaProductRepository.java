package org.saleslist.repository.datajpa;

import org.saleslist.model.Product;
import org.saleslist.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaProductRepository implements ProductRepository {

    private final CrudProductRepository crudRepository;

    public DataJpaProductRepository(CrudProductRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Product save(Product product, int userId) {

        return crudRepository.save(product);
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

    @Override
    public List<String> getOwnersNames() {
        return null;
    }
}
