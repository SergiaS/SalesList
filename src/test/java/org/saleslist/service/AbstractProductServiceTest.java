package org.saleslist.service;

import org.junit.Assert;
import org.junit.Test;
import org.saleslist.enums.DeliveryServiceEnum;
import org.saleslist.enums.MarketPlaceEnum;
import org.saleslist.enums.OrderStatusEnum;
import org.saleslist.enums.PaymentMethodEnum;
import org.saleslist.model.Product;
import org.saleslist.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static java.time.LocalDateTime.of;
import static org.junit.Assert.assertThrows;
import static org.saleslist.ProductTestData.*;
import static org.saleslist.UserTestData.ADMIN_ID;
import static org.saleslist.UserTestData.USER_ID;

public abstract class AbstractProductServiceTest extends AbstractServiceTest {

    @Autowired
    protected ProductService service;

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

    @Test
    public void createWithException() throws Exception {
        validateRootCause(() -> service.create(new Product(null, of(2020, 2, 14, 8, 47), "  ", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("215"), new BigDecimal("0"), 0, ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Product(null, null, "Glasses", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("215"), new BigDecimal("0"), 0, ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Product(null, of(2020, 2, 15, 21, 40), "Glasses", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("5001"), new BigDecimal("0"), 0, ""), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Product(null, of(2020, 2, 16, 8, 47), "Glasses", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("9"), new BigDecimal("0"), 0, ""), USER_ID), ConstraintViolationException.class);
    }
}
