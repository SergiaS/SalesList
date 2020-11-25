package org.saleslist.web;

import static org.saleslist.util.ProductsUtil.DEFAULT_PROFIT_PER_DAY;

public class SecurityUtil {

    public static final int ADMIN_ID = 100;

    private static int id = ADMIN_ID;

    public SecurityUtil() {
    }

    public static int getAuthUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }

    public static int authUserProfitPerDay() {
        return DEFAULT_PROFIT_PER_DAY;
    }
}
