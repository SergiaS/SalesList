package org.saleslist;

import org.saleslist.enums.Role;
import org.saleslist.model.User;

import java.util.Collections;
import java.util.Date;

public class UserTestData {

    public static final int START_SEQ = 101;

    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsComparator("registered", "roles");

    public static final int NOT_FOUND = 10;
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "user@ukr.net", "password", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", 700, false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        updated.setProfitPerDay(330);
        return updated;
    }
}
