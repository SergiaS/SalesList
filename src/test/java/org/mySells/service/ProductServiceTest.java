package org.mySells.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mySells.model.Product;
import org.mySells.util.exception.NotFoundException;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertThrows;
import static org.mySells.ProductTestData.*;
import static org.mySells.UserTestData.ADMIN_ID;
import static org.mySells.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class ProductServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private ProductService service;

    @Test
    public void delete() {
        service.delete(PRODUCT1_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(PRODUCT1_ID, USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, USER_ID));
    }

    @Test
    public void deleteNotOwn() {
        assertThrows(NotFoundException.class, () -> service.delete(PRODUCT1_ID, ADMIN_ID));
    }

    @Test
    public void create() {
        Product newProduct = getNew();
        Product created = service.create(newProduct, USER_ID);
        Integer newId = created.getId();
        newProduct.setId(newId);
        assertMatch(created, newProduct);
        assertMatch(service.get(newId, USER_ID), newProduct);
    }

    @Test
    public void get() {
        Product product = service.get(ADMIN_PRODUCT_ID, ADMIN_ID);
        assertMatch(product, ADMIN_PRODUCT1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, USER_ID));
    }

    @Test
    public void getNotOwn() {
        assertThrows(NotFoundException.class, () -> service.get(PRODUCT1_ID, ADMIN_ID));
    }

    @Test
    public void update() {
        Product updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(PRODUCT1_ID, USER_ID), updated);
    }

    @Test
    public void updateNotOwn() {
        assertThrows(NotFoundException.class, () -> service.update(PRODUCT1, ADMIN_ID));
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(USER_ID), PRODUCTS);
    }

    @Test
    public void getBetweenInclusive() {
        assertMatch(service.getBetweenInclusive(
                LocalDate.of(2020, Month.OCTOBER, 31),
                LocalDate.of(2020, Month.OCTOBER, 31), USER_ID),
                PRODUCT3, PRODUCT2, PRODUCT1);
    }

    @Test
    public void getBetweenWithNullDates() {
        assertMatch(service.getBetweenInclusive(null, null, USER_ID), PRODUCTS);
    }
}
