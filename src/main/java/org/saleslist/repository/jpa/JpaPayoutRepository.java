package org.saleslist.repository.jpa;

import org.saleslist.model.Payout;
import org.saleslist.model.User;
import org.saleslist.repository.PayoutRepository;
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
public class JpaPayoutRepository implements PayoutRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Payout save(Payout payout, int userId) {
        if (payout.isNew()) {
            payout.setAmount(payout.getAmount().negate());
        }
        payout.setUser(em.getReference(User.class, userId));
        if (payout.isNew()) {
            em.persist(payout);
            return payout;
        } else if (get(payout.getId(), userId) == null) {
            return null;
        }
        return em.merge(payout);
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        if (userId == ADMIN_ID) {
            return em.createNamedQuery(Payout.ADMIN_DELETE)
                    .setParameter("id", id)
                    .executeUpdate() != 0;
        }
        return em.createNamedQuery(Payout.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Payout get(int id, int userId) {
        Payout payout = em.find(Payout.class, id);
        if (userId == ADMIN_ID) {
            return payout;
        }
        return payout != null && payout.getUser().getId() == userId ? payout : null;
    }

    @Override
    public List<Payout> getAll(int userId) {
        if (userId == ADMIN_ID) {
            return em.createNamedQuery(Payout.ADMIN_ALL_SORTED, Payout.class)
                    .getResultList();
        }
        return em.createNamedQuery(Payout.ALL_SORTED, Payout.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Payout> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        if (userId == ADMIN_ID) {
            return em.createNamedQuery(Payout.ADMIN_GET_BETWEEN, Payout.class)
                    .setParameter("startDateTime", startDateTime)
                    .setParameter("endDateTime", endDateTime)
                    .getResultList();
        }
        return em.createNamedQuery(Payout.GET_BETWEEN, Payout.class)
                .setParameter("userId", userId)
                .setParameter("startDateTime", startDateTime)
                .setParameter("endDateTime", endDateTime)
                .getResultList();
    }

    @Override
    public List<String> getOwnersNames() {
        return em.createNamedQuery(Payout.ADMIN_GET_OWNERS_NAMES, String.class)
                .getResultStream().collect(Collectors.toList());
    }
}
