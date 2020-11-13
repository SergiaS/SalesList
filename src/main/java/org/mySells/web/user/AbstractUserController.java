package org.mySells.web.user;

import org.mySells.model.User;
import org.mySells.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mySells.util.ValidationUtil.assureIdConsistent;
import static org.mySells.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.info("update user={} with id={}", user, id);
        assureIdConsistent(user, id);
        service.update(user);
    }

    public User getByNickname(String nickname) {
        log.info("getByNickname {}", nickname);
        return service.getByNickname(nickname);
    }

}
