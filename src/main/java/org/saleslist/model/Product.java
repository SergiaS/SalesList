package org.saleslist.model;

import org.saleslist.enums.DeliveryServiceEnum;
import org.saleslist.enums.MarketPlaceEnum;
import org.saleslist.enums.OrderStatusEnum;
import org.saleslist.enums.PaymentMethodEnum;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Product extends AbstractBaseEntity {
    private LocalDateTime dateTime;
    private String title;

    private MarketPlaceEnum marketPlace;
    private DeliveryServiceEnum deliveryService;
    private PaymentMethodEnum paymentMethod;
    private OrderStatusEnum orderStatus;

    private BigDecimal soldAtPrice;
    private BigDecimal spent;
    private int payoutPercentage;
    private BigDecimal payoutCurrency;
    private BigDecimal profit;

    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
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
