package org.saleslist.repository.jpa;

import org.saleslist.model.Product;
import org.saleslist.model.User;
import org.saleslist.repository.ProductRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.saleslist.web.SecurityUtil.ADMIN_ID;

@Repository
@Transactional(readOnly = true)
public class JpaProductRepository implements ProductRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Product save(Product product, int userId) {
        product.setUser(em.getReference(User.class, userId));
        if (product.isNew()) {
            em.persist(product);
        } else if (get(product.getId(), userId) == null) {
            return null;
        } else {
            em.merge(product);
        }

        // for payout
        if (product.getPayoutPercentage() > 0) {
            // UPSERT in JPQL doesn't exist
            em.createNativeQuery("INSERT INTO payouts(user_id, product_id, date_time, amount, notes) " +
                    "VALUES (:userId, :productId, :dateTime, :amount, :notes) " +
                    "ON CONFLICT (product_id) " +
                    "DO UPDATE SET user_id=EXCLUDED.user_id, date_time=EXCLUDED.date_time, amount=EXCLUDED.amount, notes=EXCLUDED.notes")
                    .setParameter("userId", userId)
                    .setParameter("productId", product.getId())
                    .setParameter("dateTime", product.getDateTime())
                    .setParameter("amount", product.getPayoutCurrency())
                    .setParameter("notes", product.getTitle())
                    .executeUpdate();
        } else {
            em.createQuery("DELETE FROM Payout pa WHERE pa.user.id=:productId")
//            em.createNativeQuery("DELETE FROM payouts WHERE product_id=:productId")
                    .setParameter("productId", product.getId())
                    .executeUpdate();
        }

        return product;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        if (userId == ADMIN_ID) {
            return em.createNamedQuery(Product.ADMIN_DELETE)
                    .setParameter("id", id)
                    .executeUpdate() != 0;
        }
        return em.createNamedQuery(Product.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Product get(int id, int userId) {
        Product product = em.find(Product.class, id);
        if (userId == ADMIN_ID) {
            return product;
        } else {
            return product != null && product.getUser().getId() == userId ? product : null;
        }
    }

    @Override
    public List<Product> getAll(int userId) {
        if (userId == ADMIN_ID) {
            return em.createNamedQuery(Product.ADMIN_ALL_SORTED, Product.class)
                    .getResultList();
        }
        return em.createNamedQuery(Product.ALL_SORTED, Product.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Product> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        if (userId == ADMIN_ID) {
            return em.createNamedQuery(Product.ADMIN_GET_BETWEEN, Product.class)
                    .setParameter("startDateTime", startDateTime)
                    .setParameter("endDateTime", endDateTime)
                    .getResultList();
        }
        return em.createNamedQuery(Product.GET_BETWEEN, Product.class)
                .setParameter("userId", userId)
                .setParameter("startDateTime", startDateTime)
                .setParameter("endDateTime", endDateTime)
                .getResultList();
    }

    @Override
    public List<String> getOwnersNames() {
        return em.createNamedQuery(Product.ADMIN_GET_OWNERS_NAMES, String.class)
                .getResultStream().collect(Collectors.toList());
    }
}
