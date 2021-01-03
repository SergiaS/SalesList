package org.saleslist.repository.datajpa;

import org.saleslist.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudProductRepository extends JpaRepository<Product, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Product p WHERE p.id = :id AND p.user.id = :userId")
    int delete(@Param("id") int id, @Param("userId") int userId);
//    deleteProductWhereIdAndUserid

    @Query("SELECT p FROM Product p WHERE p.user.id = :userId ORDER BY p.dateTime DESC")
    List<Product> getAll(@Param("userId") int userId);

    @Query("SELECT p FROM Product p WHERE p.dateTime >= :startDate AND p.dateTime < :endDate AND p.user.id = :userId ORDER BY p.dateTime DESC")
    List<Product> getBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);

    @Query("SELECT p FROM Product p WHERE p.dateTime >= :startDate AND p.dateTime < :endDate ORDER BY p.dateTime DESC")
    List<Product> getBetweenAdmin(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

//    @Query(Product.ADMIN_GET_OWNERS_NAMES)
    @Query("SELECT u.name FROM Product p INNER JOIN User u ON u.id = p.user.id ORDER BY p.dateTime DESC")
    List<String> getOwnersNames();
}
