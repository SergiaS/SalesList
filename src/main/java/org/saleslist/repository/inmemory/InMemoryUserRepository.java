package org.saleslist.repository.inmemory;

import org.saleslist.model.User;
import org.saleslist.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepository extends InMemoryBaseRepository<User> implements UserRepository {

    static final int USER_ID = 1;
    static final int ADMIN_ID = 2;

    @Override
    public List<User> getAll() {
        return getCollection().stream()
                .sorted(Comparator.comparing(User::getNickname))
                .collect(Collectors.toList());
    }

    @Override
    public User getByNickname(String nickname) {
        return getCollection().stream()
                .filter(user -> nickname.equals(user.getNickname()))
                .findFirst()
                .orElse(null);
    }
}
