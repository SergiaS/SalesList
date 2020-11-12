package ru.javawebinar.mySells.repository.inmemory;

import org.saleslist.model.User;
import org.saleslist.repository.UserRepository;
import org.springframework.stereotype.Repository;
import ru.javawebinar.mySells.UserTestData;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.mySells.UserTestData.ADMIN;
import static ru.javawebinar.mySells.UserTestData.USER;

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
