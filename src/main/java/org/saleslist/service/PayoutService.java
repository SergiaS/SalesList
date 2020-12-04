package org.saleslist.service;

import org.saleslist.model.Payout;
import org.saleslist.repository.jdbc.JdbcMainRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static org.saleslist.util.DateTimeUtil.atStartOfDayOrMin;
import static org.saleslist.util.DateTimeUtil.atStartOfNextDayOrMax;

@Service
public class PayoutService {

    private final JdbcMainRepository<Payout> repository;

    public PayoutService(JdbcMainRepository<Payout> repository) {
        this.repository = repository;
    }

    public Payout get(int id, int userId) {
//        checkNotFoundWithId(repository.get(id, userId), id);
        // needs check product_id
        return repository.get(id, userId);
    }

    public void delete(int id, int userId) {
//        checkNotFoundWithId(repository.delete(id, userId), id);
        // needs check product_id
        repository.delete(id, userId);
    }

    public List<Payout> getBetweenInclusive(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId) {
        return repository.getBetween(atStartOfDayOrMin(startDate), atStartOfNextDayOrMax(endDate), userId);
    }

    public List<Payout> getAll(int userId) {
        return repository.getAll(userId);
    }

    public void update(Payout payout, int userId) {
        Assert.notNull(payout, "payout must be not null");
        repository.save(payout, userId);
    }

    public Payout create(Payout payout, int userId) {
        Assert.notNull(payout, "payout must be not null");
        return repository.save(payout, userId);
    }
}
