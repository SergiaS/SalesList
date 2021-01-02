package org.saleslist.service;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.saleslist.ActiveDbProfileResolver;
import org.saleslist.model.Product;
import org.saleslist.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertThrows;
import static org.saleslist.ProductTestData.*;
import static org.saleslist.UserTestData.ADMIN_ID;
import static org.saleslist.UserTestData.USER_ID;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Test for use with HSQLDB only!
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/hsqldb_populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
public class ProductServiceTest {
    private static final Logger log = getLogger("result");

    private static final StringBuilder results = new StringBuilder();

    @Rule
    public final Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("\n%-25s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result);
            log.info(result + " ms\n");
        }
    };

    @Autowired
    private ProductService service;

    @AfterClass
    public static void printResult() {
        log.info("\n---------------------------------" +
                "\nTest                 Duration, ms" +
                "\n---------------------------------" +
                results +
                "\n---------------------------------");
    }

    @Test
    public void delete() throws Exception {
        service.delete(PRODUCT1_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(PRODUCT1_ID, USER_ID));
    }

    @Test
    public void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, USER_ID));
    }

    @Test
    public void deleteNotOwn() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(PRODUCT1_ID, ADMIN_ID));
    }

    @Test
    public void create() throws Exception {
        Product created = service.create(getNew(), USER_ID);
        int newId = created.getId() == null ? 0 : created.getId();

        Product newProduct = getNew();
        newProduct.setId(newId);
        PRODUCT_MATCHER.assertMatch(created, newProduct);
        PRODUCT_MATCHER.assertMatch(service.get(newId, USER_ID), newProduct);
    }

    @Test
    public void get() throws Exception {
        Product actual = service.get(ADMIN_PRODUCT_ID, ADMIN_ID);
        PRODUCT_MATCHER.assertMatch(actual, ADMIN_PRODUCT1);
    }

    @Test
    public void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, ADMIN_ID));
    }

    @Test
    public void getNotOwn() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(PRODUCT1_ID, ADMIN_ID));
    }

    @Test
    public void update() throws Exception {
        Product updated = getUpdated();
        service.update(updated, USER_ID);
        PRODUCT_MATCHER.assertMatch(service.get(PRODUCT1_ID, USER_ID), getUpdated());
    }

    @Test
    public void updateNotOwn() throws Exception {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.update(PRODUCT1, ADMIN_ID));
        Assert.assertEquals("Not found entity with id=" + PRODUCT1_ID, exception.getMessage());
    }

    @Test
    public void getAll() throws Exception {
        PRODUCT_MATCHER.assertMatch(service.getAll(USER_ID), PRODUCTS);
    }

    @Test
    public void getBetweenInclusive() throws Exception {
        PRODUCT_MATCHER.assertMatch(service.getBetweenInclusive(
                LocalDate.of(2020, Month.FEBRUARY, 14),
                LocalDate.of(2020, Month.FEBRUARY, 15), USER_ID),
                PRODUCT3, PRODUCT2, PRODUCT1);
    }

    @Test
    public void getBetweenWithNullDates() throws Exception {
        PRODUCT_MATCHER.assertMatch(service.getBetweenInclusive(null, null, USER_ID), PRODUCTS);
    }
}
