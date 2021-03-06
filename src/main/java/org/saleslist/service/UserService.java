package org.saleslist.service;

import org.saleslist.model.User;
import org.saleslist.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
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

    @Cacheable("users")
    public List<User> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        // needs user check -
        checkNotFoundWithId(repository.save(user), user.getId());
//        repository.save(user)
    }

    public User getWithProducts(int id) {
        return checkNotFoundWithId(repository.getWithProducts(id), id);
    }

    public User getWithPayouts(int id) {
        return checkNotFoundWithId(repository.getWithProducts(id), id);
    }
}
