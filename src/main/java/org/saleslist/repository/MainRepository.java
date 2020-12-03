package org.saleslist.repository;

import java.time.LocalDateTime;
import java.util.List;

public interface MainRepository<T> {
    T save(T model, int userId);

    boolean delete(int id, int userId);

    T get(int id, int userId);

    List<T> getAll(int userId);

    List<T> getBetweenDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);
}
