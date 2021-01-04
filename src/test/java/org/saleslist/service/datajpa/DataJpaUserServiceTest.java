package org.saleslist.service.datajpa;

import org.junit.Assert;
import org.junit.Test;
import org.saleslist.ProductTestData;
import org.saleslist.model.User;
import org.saleslist.service.AbstractUserServiceTest;
import org.saleslist.util.exception.NotFoundException;
import org.springframework.test.context.ActiveProfiles;

import static org.saleslist.Profiles.DATAJPA;
import static org.saleslist.UserTestData.*;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {

    @Test
    public void getWithProducts() throws Exception {
        User user = service.getWithProducts(USER_ID);
        USER_MATCHER.assertMatch(user, USER);
        ProductTestData.PRODUCT_MATCHER.assertMatch(user.getProducts(), ProductTestData.PRODUCTS);
    }

    @Test
    public void getWithProductsNotFound() throws Exception {
        Assert.assertThrows(NotFoundException.class,
                () -> service.getWithProducts(1));
    }
}
