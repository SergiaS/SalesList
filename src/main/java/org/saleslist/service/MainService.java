package org.saleslist.service;

import org.saleslist.model.AbstractBaseEntity;
import org.saleslist.repository.jdbc.JdbcMainRepository;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static org.saleslist.util.DateTimeUtil.atStartOfDayOrMin;
import static org.saleslist.util.DateTimeUtil.atStartOfNextDayOrMax;
import static org.saleslist.util.ValidationUtil.checkNotFoundWithId;


public abstract class MainService<T extends AbstractBaseEntity> {

    private final JdbcMainRepository<T> repository;

    public MainService(JdbcMainRepository<T> repository) {
        this.repository = repository;
    }

    public T get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public List<T> getBetweenInclusive(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId) {
        return repository.getBetween(atStartOfDayOrMin(startDate), atStartOfNextDayOrMax(endDate), userId);
    }

    public List<T> getAll(int userId) {
        return repository.getAll(userId);
    }

    public void update(T model, int userId) {
        Assert.notNull(model, "model must be not null");
        repository.save(model, userId);
    }

    public T create(T model, int userId) {
        Assert.notNull(model, "model must be not null");
        return repository.save(model, userId);
    }
}
