package org.saleslist.web;

import static org.saleslist.util.ProductsUtil.DEFAULT_PROFIT_PER_DAY;

public class SecurityUtil {

    private static int id = 101;

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
