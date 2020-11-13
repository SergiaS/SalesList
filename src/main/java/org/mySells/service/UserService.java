package org.mySells.service;

import org.mySells.model.User;
import org.mySells.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.mySells.util.ValidationUtil.checkNotFound;
import static org.mySells.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        return repository.save(user);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User getByNickname(String nickname) {
        return checkNotFound(repository.getByNickname(nickname), "nickname=" + nickname);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public void update(User user) {
        checkNotFoundWithId(repository.save(user), user.getId());
    }
}
