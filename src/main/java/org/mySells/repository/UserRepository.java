package org.mySells.repository;

import org.mySells.model.User;

import java.util.List;

public interface UserRepository {
    // null if not found, when updated
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not null
    User get(int id);

    // null if not found
    User getByNickname(String nickname);

    List<User> getAll();
}
