package org.saleslist.jdbc.repository;

import org.saleslist.jdbc.model.Product;

import java.util.List;

public interface ProductRepository {

	Product save(Product product);

	Product getById(int id);

	Product update(int id, Product product);

	boolean delete(int id);

	List<Product> getAllProducts();
}
