package org.mySells.repository.inmemory;

import org.mySells.UserTestData;
import org.mySells.model.User;
import org.mySells.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.mySells.UserTestData.ADMIN;
import static org.mySells.UserTestData.USER;

@Repository
public class InMemoryUserRepository extends InMemoryBaseRepository<User> implements UserRepository {

    public void init() {
        map.clear();
        map.put(UserTestData.USER_ID, USER);
        map.put(UserTestData.ADMIN_ID, ADMIN);
    }

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
