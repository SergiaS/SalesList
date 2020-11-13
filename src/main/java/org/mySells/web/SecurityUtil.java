package org.mySells.web;

import static org.mySells.util.ProductsUtil.PROFIT_PER_DAY;

public class SecurityUtil {

    private static int id = 1;

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }

    public static int authUserProfitsPerDay() {
        return PROFIT_PER_DAY;
    }
}
