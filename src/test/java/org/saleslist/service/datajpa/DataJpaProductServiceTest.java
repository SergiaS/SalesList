package org.saleslist.service.datajpa;

import org.junit.Assert;
import org.junit.Test;
import org.saleslist.UserTestData;
import org.saleslist.model.Product;
import org.saleslist.service.AbstractProductServiceTest;
import org.saleslist.util.exception.NotFoundException;
import org.springframework.test.context.ActiveProfiles;

import static org.saleslist.ProductTestData.*;
import static org.saleslist.Profiles.DATAJPA;
import static org.saleslist.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
public class DataJpaProductServiceTest extends AbstractProductServiceTest {

    @Test
    public void getWithUser() throws Exception {
        Product adminProduct = service.getWithUser(ADMIN_PRODUCT_ID, ADMIN_ID);
        PRODUCT_MATCHER.assertMatch(adminProduct, ADMIN_PRODUCT1);
        UserTestData.USER_MATCHER.assertMatch(adminProduct.getUser(), UserTestData.ADMIN);
    }

    @Test
    public void getWithUserNotFound() throws Exception {
        Assert.assertThrows(NotFoundException.class,
                () -> service.getWithUser(1, ADMIN_ID));
    }
}
