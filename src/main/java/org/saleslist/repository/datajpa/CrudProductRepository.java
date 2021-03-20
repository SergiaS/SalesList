package org.saleslist.repository.datajpa;

import org.saleslist.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudProductRepository extends JpaRepository<Product, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.id = :id AND p.user.id = :userId")
    int delete(@Param("id") int id, @Param("userId") int userId);
//    deleteProductWhereIdAndUserid

    @Query("SELECT p FROM Product p WHERE p.user.id = :userId ORDER BY p.dateTime DESC")
    List<Product> getAll(@Param("userId") int userId);

    @Query("SELECT p FROM Product p WHERE p.dateTime >= :startDate AND p.dateTime < :endDate AND p.user.id = :userId ORDER BY p.dateTime DESC")
    List<Product> getBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);

    @Query("SELECT p FROM Product p WHERE p.dateTime >= :startDate AND p.dateTime < :endDate ORDER BY p.dateTime DESC")
    List<Product> getBetweenAdmin(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT p FROM Product p JOIN FETCH p.user WHERE p.id=?1 AND p.user.id=?2")
    Product getWithUser(int id, int userId);

    @Modifying
    @Transactional(propagation = Propagation.NESTED)
    @Query("UPDATE Payout p SET p.dateTime=?1, p.amount=?2, p.notes=?3 WHERE p.product.id=?4")
    void updatePayout(LocalDateTime dateTime, BigDecimal payoutCurrency, String title, int productId);

//    @Query(Product.ADMIN_GET_OWNERS_NAMES)
    @Query("SELECT u.name FROM Product p INNER JOIN User u ON u.id = p.user.id ORDER BY p.dateTime DESC")
    List<String> getOwnersNames();
}
