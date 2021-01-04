package org.saleslist.repository;

import org.saleslist.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();

    default User getWithProducts(int id) {
        throw new UnsupportedOperationException();
    }

    default User getWithPayouts(int id) {
        throw new UnsupportedOperationException();
    }
}
