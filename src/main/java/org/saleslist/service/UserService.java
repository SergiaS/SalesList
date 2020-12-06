package org.saleslist.service;

import org.saleslist.model.User;
import org.saleslist.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static org.saleslist.util.ValidationUtil.checkNotFound;
import static org.saleslist.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    public void delete(int id) {
        // needs user check -
        checkNotFoundWithId(repository.delete(id), id);
//        repository.delete(id);
    }

    public User get(int id) {
        // needs user check -
        return checkNotFoundWithId(repository.get(id), id);
//        return repository.get(id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must be not null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        // needs user check -
        checkNotFoundWithId(repository.save(user), user.getId());
//        repository.save(user)
    }
}
