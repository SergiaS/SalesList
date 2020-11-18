package org.mySells;

import org.mySells.model.Role;
import org.mySells.model.User;

import java.util.Collections;
import java.util.Date;

import static org.mySells.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsComparator("registered", "roles");

    public static final int NOT_FOUND = 10;
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "password", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin", Role.ADMIN);

    public static User getNew() {
        return new User(null, "New","123456",500, false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(USER);
        updated.setNickname("UpdatedName");
        updated.setProfitsPerDay(330);
        return updated;
    }
}
