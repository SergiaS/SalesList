package org.saleslist.repository.datajpa;

import org.saleslist.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudProductRepository extends JpaRepository<Product, Integer> {

}
