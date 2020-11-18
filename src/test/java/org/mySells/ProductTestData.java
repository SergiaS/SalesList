package org.mySells;

import org.mySells.enums.DeliveryServiceEnum;
import org.mySells.enums.MarketPlaceEnum;
import org.mySells.enums.OrderStatusEnum;
import org.mySells.enums.PaymentMethodEnum;
import org.mySells.model.Product;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mySells.model.AbstractBaseEntity.START_SEQ;

public class ProductTestData {

    public static final int NOT_FOUND = 10;
    public static final int PRODUCT1_ID = START_SEQ + 2;
    public static final int ADMIN_PRODUCT_ID = START_SEQ + 9;

    public static final Product PRODUCT1 = new Product(PRODUCT1_ID, of(2020, Month.OCTOBER, 31, 8, 18), "Glasses", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("100"), new BigDecimal("0.0"), 40, new BigDecimal("40"), new BigDecimal("60"), "");
    public static final Product PRODUCT2 = new Product(PRODUCT1_ID + 1, of(2020, Month.OCTOBER, 31, 10, 43), "Chain", MarketPlaceEnum.FACEBOOK, DeliveryServiceEnum.JUSTIN, PaymentMethodEnum.COMPLETE_PREPAYMENT, OrderStatusEnum.SUCCESS, new BigDecimal("350"), new BigDecimal("150.0"), 0, new BigDecimal("0"), new BigDecimal("200"), "");
    public static final Product PRODUCT3 = new Product(PRODUCT1_ID + 2, of(2020, Month.OCTOBER, 31, 13, 24), "Glasses", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("400"), new BigDecimal("0.0"), 40, new BigDecimal("160"), new BigDecimal("240"), "");
    public static final Product PRODUCT4 = new Product(PRODUCT1_ID + 3, of(2020, Month.NOVEMBER, 1, 7, 51), "Glasses", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("100"), new BigDecimal("0.0"), 40, new BigDecimal("40"), new BigDecimal("60"), "");
    public static final Product PRODUCT5 = new Product(PRODUCT1_ID + 4, of(2020, Month.NOVEMBER, 1, 9, 17), "Bottles", MarketPlaceEnum.SITE, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.SAVE_SERVICE, OrderStatusEnum.SUCCESS, new BigDecimal("600"), new BigDecimal("270"), 0, new BigDecimal("0"), new BigDecimal("330"), "");
    public static final Product PRODUCT6 = new Product(PRODUCT1_ID + 5, of(2020, Month.NOVEMBER, 1, 14, 30), "Glasses", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("100"), new BigDecimal("0.0"), 40, new BigDecimal("40"), new BigDecimal("60"), "");
    public static final Product ADMIN_PRODUCT1 = new Product(ADMIN_PRODUCT_ID, of(2020, Month.NOVEMBER, 1, 14, 30), "Glasses", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("100"), new BigDecimal("0.0"), 40, new BigDecimal("40"), new BigDecimal("60"), "");
    public static final Product ADMIN_PRODUCT2 = new Product(ADMIN_PRODUCT_ID + 1, of(2020, Month.NOVEMBER, 1, 14, 30), "Glasses", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("100"), new BigDecimal("0.0"), 40, new BigDecimal("40"), new BigDecimal("60"), "");

    public static final List<Product> PRODUCTS = Arrays.asList(PRODUCT6,PRODUCT5,PRODUCT4,PRODUCT3,PRODUCT2,PRODUCT1);

    public static Product getNew() {
        return new Product(null, of(2020, Month.FEBRUARY, 1, 18, 0), "New item", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("100"), new BigDecimal("0.0"), 40, new BigDecimal("40"), new BigDecimal("60"), "");
    }

    public static Product getUpdated() {
        return new Product(PRODUCT1_ID, PRODUCT1.getDateTime(), "Updated item", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("100"), new BigDecimal("0.0"), 30, new BigDecimal("30"), new BigDecimal("70"), "");
    }

    public static void assertMatch(Product actual, Product expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Product> actual, Product... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Product> actual, Iterable<Product> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }
}
