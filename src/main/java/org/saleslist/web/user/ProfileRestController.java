package org.saleslist.web.user;

import org.saleslist.model.User;

import static org.saleslist.web.SecurityUtil.getAuthUserId;

public class ProfileRestController extends AbstractUserController {

    @Override
    public User get(int id) {
        return super.get(getAuthUserId());
    }

    @Override
    public void delete(int id) {
        super.delete(getAuthUserId());
    }

    @Override
    public void update(User user, int id) {
        super.update(user, getAuthUserId());
    }
}
