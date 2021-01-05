package org.saleslist.model;

import org.hibernate.validator.constraints.Range;
import org.saleslist.enums.DeliveryServiceEnum;
import org.saleslist.enums.MarketPlaceEnum;
import org.saleslist.enums.OrderStatusEnum;
import org.saleslist.enums.PaymentMethodEnum;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NamedQueries({
        @NamedQuery(name = Product.DELETE, query = "DELETE FROM Product p WHERE p.id=:id AND p.user.id=:userId"),
        @NamedQuery(name = Product.ALL_SORTED, query = "SELECT p FROM Product p WHERE p.user.id=:userId ORDER BY p.dateTime DESC"),
        @NamedQuery(name = Product.GET_BETWEEN, query = "SELECT p FROM Product p WHERE p.user.id=:userId AND p.dateTime >= :startDateTime AND p.dateTime < :endDateTime ORDER BY p.dateTime DESC"),
        @NamedQuery(name = Product.ADMIN_DELETE, query = "DELETE FROM Product p WHERE p.id=:id"),
        @NamedQuery(name = Product.ADMIN_ALL_SORTED, query = "SELECT p FROM Product p ORDER BY p.dateTime DESC"),
        @NamedQuery(name = Product.ADMIN_GET_BETWEEN, query = "SELECT p FROM Product p WHERE p.dateTime >= :startDateTime AND p.dateTime < :endDateTime ORDER BY p.dateTime DESC"),
        @NamedQuery(name = Product.ADMIN_GET_OWNERS_NAMES, query = "SELECT u.name FROM Product p INNER JOIN User u ON u.id = p.user.id ORDER BY p.dateTime DESC"),
})
@Entity
@Table(name = "products")
public class Product extends AbstractBaseEntity {
    public static final String DELETE = "Product.delete";
    public static final String ALL_SORTED = "Product.getAll";
    public static final String GET_BETWEEN = "Product.getBetween";
    public static final String ADMIN_DELETE = "Product.adminDelete";
    public static final String ADMIN_ALL_SORTED = "Product.adminGetAll";
    public static final String ADMIN_GET_BETWEEN = "Product.adminGetBetween";
    public static final String ADMIN_GET_OWNERS_NAMES = "Product.getOwnersNames";

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "title", nullable = false)
    @NotBlank
    @Size(min = 4)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "market_place", nullable = false)
    private MarketPlaceEnum marketPlace;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_service", nullable = false)
    private DeliveryServiceEnum deliveryService;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethodEnum paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatusEnum orderStatus;

    @Column(name = "sold_at_price", nullable = false)
    @Range(min = 0)
    private BigDecimal soldAtPrice;

    @Column(name = "spent", nullable = false)
    @Range(min = 0)
    private BigDecimal spent;

    @Column(name = "payout_percentage", nullable = false)
    @Range(min = 0, max = 100)
    private int payoutPercentage;

    @Column(name = "payout_currency", nullable = false)
    private BigDecimal payoutCurrency;

    @Column(name = "profit", nullable = false)
    @Range(min = 10, max = 5000)
    private BigDecimal profit;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Product() {
    }

    public Product(LocalDateTime dateTime, String title, MarketPlaceEnum marketPlace, DeliveryServiceEnum deliveryService, PaymentMethodEnum paymentMethod, OrderStatusEnum orderStatus, BigDecimal soldAtPrice, BigDecimal spent, int payoutPercentage, String notes) {
        this(null, dateTime, title, marketPlace, deliveryService, paymentMethod, orderStatus, soldAtPrice, spent, payoutPercentage, notes);
    }

    public Product(Integer id, LocalDateTime dateTime, String title, MarketPlaceEnum marketPlace, DeliveryServiceEnum deliveryService, PaymentMethodEnum paymentMethod, OrderStatusEnum orderStatus, BigDecimal soldAtPrice, BigDecimal spent, int payoutPercentage, String notes) {
        super(id);
        this.dateTime = dateTime;
        this.title = title;
        this.marketPlace = marketPlace;
        this.deliveryService = deliveryService;
        this.paymentMethod = paymentMethod;
        this.orderStatus = orderStatus;
        this.soldAtPrice = soldAtPrice;
        this.spent = spent;
        this.payoutPercentage = payoutPercentage;
        this.notes = notes;

        this.payoutCurrency = payoutCurrencyCalculation();
        this.profit = profitCalculation();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MarketPlaceEnum getMarketPlace() {
        return marketPlace;
    }

    public void setMarketPlace(MarketPlaceEnum marketPlace) {
        this.marketPlace = marketPlace;
    }

    public DeliveryServiceEnum getDeliveryService() {
        return deliveryService;
    }

    public void setDeliveryService(DeliveryServiceEnum deliveryService) {
        this.deliveryService = deliveryService;
    }

    public PaymentMethodEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getSoldAtPrice() {
        return soldAtPrice;
    }

    public void setSoldAtPrice(BigDecimal soldAtPrice) {
        this.soldAtPrice = soldAtPrice;
    }

    public BigDecimal getSpent() {
        return spent;
    }

    public void setSpent(BigDecimal spent) {
        this.spent = spent;
    }

    public int getPayoutPercentage() {
        return payoutPercentage;
    }

    public void setPayoutPercentage(int payoutPercentage) {
        this.payoutPercentage = payoutPercentage;
    }

    public BigDecimal getPayoutCurrency() {
        return payoutCurrency;
    }

    public void setPayoutCurrency(BigDecimal payoutCurrency) {
        this.payoutCurrency = payoutCurrency;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal payoutCurrencyCalculation() {
        if (payoutPercentage > 0) {
            double calculatePercent = payoutPercentage / 100.0;
            BigDecimal tmp = soldAtPrice.subtract(spent);
            String multiply = tmp.multiply(BigDecimal.valueOf(calculatePercent)).setScale(2, RoundingMode.CEILING).stripTrailingZeros().toPlainString();
            return new BigDecimal(multiply);
        }
        return new BigDecimal(BigInteger.ZERO);
    }

    public BigDecimal profitCalculation() {
        if (payoutPercentage == 0 && spent.compareTo(BigDecimal.ZERO) != 0) {
            return soldAtPrice.subtract(spent);
        } else if (spent.compareTo(BigDecimal.ZERO) == 0 && payoutPercentage != 0) {
            return soldAtPrice.subtract(payoutCurrency);
        } else if ((payoutPercentage != 0 && spent.compareTo(BigDecimal.ZERO) != 0) || (payoutPercentage == 0 && spent.compareTo(BigDecimal.ZERO) <= 0)) {
            return soldAtPrice.subtract(spent).subtract(payoutCurrency);
        } else {
            return soldAtPrice;
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", title='" + title + '\'' +
                ", marketPlace=" + marketPlace +
                ", deliveryService=" + deliveryService +
                ", paymentMethod=" + paymentMethod +
                ", orderStatus=" + orderStatus +
                ", soldAtPrice=" + soldAtPrice +
                ", spent=" + spent +
                ", payoutPercentage=" + payoutPercentage +
                ", payoutCurrency=" + payoutCurrency +
                ", profit=" + profit +
                ", notes='" + notes + '\'' +
                '}';
    }
}
