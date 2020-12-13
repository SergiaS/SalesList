package org.saleslist.repository.jpa;

import org.saleslist.repository.MainRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JpaPayoutRepository implements MainRepository {
    @Override
    public Object save(Object model, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Object get(int id, int userId) {
        return null;
    }

    @Override
    public List getAll(int userId) {
        return null;
    }

    @Override
    public List getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return null;
    }
}
