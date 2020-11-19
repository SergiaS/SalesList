package org.mySells.web;

import org.mySells.model.AbstractBaseEntity;

import static org.mySells.util.ProductsUtil.DEFAULT_PROFIT_PER_DAY;

public class SecurityUtil {

    private static int id = AbstractBaseEntity.START_SEQ;

    public SecurityUtil() {
    }

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }

    public static int authUserProfitsPerDay() {
        return DEFAULT_PROFIT_PER_DAY;
    }
}
