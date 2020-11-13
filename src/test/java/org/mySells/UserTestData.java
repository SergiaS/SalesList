package org.mySells;

import org.mySells.model.Role;
import org.mySells.model.User;

public class UserTestData {
    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;

    public static final User USER = new User(USER_ID, "User999", "password", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin777", "admin", Role.ADMIN);
}