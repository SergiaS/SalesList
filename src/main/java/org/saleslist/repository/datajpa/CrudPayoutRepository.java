package org.saleslist.repository.datajpa;

import org.saleslist.model.Payout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudPayoutRepository extends JpaRepository<Payout, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Payout pa WHERE pa.id=:id AND pa.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Payout pa WHERE pa.product.id=:productId")
    void deleteByProductId(@Param("productId") int productId);

//    @Query("SELECT pa FROM Payout pa WHERE pa.user.id=:userId")
//    List<Payout> getAll(@Param("userId") int userId);

    @Query("SELECT pa FROM Payout pa WHERE pa.dateTime>=:startDate AND pa.dateTime<:endDate AND pa.user.id=:userId ORDER BY pa.dateTime DESC")
    List<Payout> getBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);

    @Query("SELECT pa FROM Payout pa WHERE pa.dateTime>=:startDate AND pa.dateTime<:endDate ORDER BY pa.dateTime DESC")
    List<Payout> getBetweenAdmin(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT u.name FROM Payout pa INNER JOIN User u ON u.id = pa.user.id ORDER BY pa.dateTime DESC")
    List<String> getOwnersNames();

    @Query("SELECT pa FROM Payout pa JOIN FETCH pa.user WHERE pa.id=?1 AND pa.user.id=?2")
    Payout getWithUser(int id, int userId);
}
