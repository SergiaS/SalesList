package org.saleslist;

import org.saleslist.enums.DeliveryServiceEnum;
import org.saleslist.enums.MarketPlaceEnum;
import org.saleslist.enums.OrderStatusEnum;
import org.saleslist.enums.PaymentMethodEnum;
import org.saleslist.model.Product;

import java.math.BigDecimal;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.saleslist.UserTestData.START_SEQ;

/**
 * Test for use with HSQLDB only!
 */
//@ContextConfiguration({
//        "classpath:spring/spring-app.xml",
//        "classpath:spring/spring-db.xml"
//})
//@RunWith(SpringRunner.class)
//@Sql(scripts = "classpath:db/copyFromBackup.sql", config = @SqlConfig(encoding = "UTF-8"))
public class ProductTestData {
    public static TestMatcher<Product> PRODUCT_MATCHER = TestMatcher.usingFieldsComparator();

    public static final int NOT_FOUND = 10;
    public static final int PRODUCT1_ID = START_SEQ + 2;
    public static final int ADMIN_PRODUCT_ID = START_SEQ + 7;

    public static final Product PRODUCT1 = new Product(PRODUCT1_ID, of(2020, 2, 14, 8, 47), "Glasses", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("215"), new BigDecimal("0"), 40, "");
    public static final Product PRODUCT2 = new Product(PRODUCT1_ID + 1, of(2020, 2, 14, 18, 32), "Glasses", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("215"), new BigDecimal("0"), 40, "");
    public static final Product PRODUCT3 = new Product(PRODUCT1_ID + 2, of(2020, 2, 15, 21, 40), "Glasses", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("215"), new BigDecimal("0"), 40, "");
    public static final Product PRODUCT4 = new Product(PRODUCT1_ID + 3, of(2020, 2, 16, 8, 47), "Glasses", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("215"), new BigDecimal("0"), 40, "");
    public static final Product PRODUCT5 = new Product(PRODUCT1_ID + 4, of(2020, 2, 19, 11, 22), "Glasses", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("215"), new BigDecimal("0"), 40, "");
    public static final Product ADMIN_PRODUCT1 = new Product(ADMIN_PRODUCT_ID, of(2020, 2, 21, 13, 31), "Omega", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("130"), new BigDecimal("0"), 0, "Карта клиента НП (+2 грн.)");
    public static final Product ADMIN_PRODUCT2 = new Product(ADMIN_PRODUCT_ID + 1, of(2020, 2, 25, 13, 21), "Headphones", MarketPlaceEnum.CONTACTS, DeliveryServiceEnum.COLLECTION_IN_PERSON, PaymentMethodEnum.CASH, OrderStatusEnum.SUCCESS, new BigDecimal("400"), new BigDecimal("0"), 0, "eBay");

    public static final List<Product> PRODUCTS = List.of(PRODUCT5, PRODUCT4, PRODUCT3, PRODUCT2, PRODUCT1);

    public static Product getNew() {
        return new Product(null, of(2020, 2, 1, 18, 0), "Some new product", MarketPlaceEnum.OLX, DeliveryServiceEnum.NOVA_POST, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, new BigDecimal("200"), new BigDecimal("115"), 0, "");
    }

    public static Product getUpdated() {
        return new Product(PRODUCT1_ID, PRODUCT1.getDateTime(), "Updated product", MarketPlaceEnum.OLX, DeliveryServiceEnum.JUSTIN, PaymentMethodEnum.COMPLETE_PREPAYMENT, OrderStatusEnum.SUCCESS, new BigDecimal("200"), new BigDecimal("0"), 40, "");
    }
}
