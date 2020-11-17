package org.mySells.model;

import org.mySells.enums.DeliveryServiceEnum;
import org.mySells.enums.MarketPlaceEnum;
import org.mySells.enums.OrderStatusEnum;
import org.mySells.enums.PaymentMethodEnum;

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
    private Integer payoutPercentage;
    private BigDecimal payoutCurrency;
    private BigDecimal profit;

    private String notes;

    public Product() {
    }

    public Product(LocalDateTime dateTime, String title, MarketPlaceEnum marketPlace, DeliveryServiceEnum deliveryService, PaymentMethodEnum paymentMethod, OrderStatusEnum orderStatus, BigDecimal soldAtPrice, BigDecimal spent, Integer payoutPercentage, String notes) {
        this(null, dateTime, title, marketPlace, deliveryService, paymentMethod, orderStatus, soldAtPrice, spent, payoutPercentage, null, null, notes);
    }

    public Product(Integer id, LocalDateTime dateTime, String title, MarketPlaceEnum marketPlace, DeliveryServiceEnum deliveryService, PaymentMethodEnum paymentMethod, OrderStatusEnum orderStatus, BigDecimal soldAtPrice, BigDecimal spent, Integer payoutPercentage, BigDecimal payoutCurrency, BigDecimal profit, String notes) {
        super(id);
        this.dateTime = dateTime;
        this.title = title;
        this.marketPlace = marketPlace;
        this.deliveryService = deliveryService;
        this.paymentMethod = paymentMethod;
        this.orderStatus = orderStatus;
        this.soldAtPrice = soldAtPrice.setScale(2, RoundingMode.CEILING);
        this.spent = spent;
        this.payoutPercentage = payoutPercentage;
        this.notes = notes;

        this.payoutCurrency = payoutCurrencyCalculation();
        this.profit = profitCalculation();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getTitle() {
        return title;
    }

    public MarketPlaceEnum getMarketPlace() {
        return marketPlace;
    }

    public DeliveryServiceEnum getDeliveryService() {
        return deliveryService;
    }

    public PaymentMethodEnum getPaymentMethod() {
        return paymentMethod;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public BigDecimal getSpent() {
        return spent;
    }

    public BigDecimal getSoldAtPrice() {
        return soldAtPrice;
    }

    public Integer getPayoutPercentage() {
        return payoutPercentage;
    }

    public BigDecimal getPayoutCurrency() {
        return payoutCurrency;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public BigDecimal payoutCurrencyCalculation() {
        if (payoutPercentage > 0) {
            double calcPers = payoutPercentage / 100.0;
            BigDecimal tmp = soldAtPrice.subtract(spent);
            return tmp.multiply(BigDecimal.valueOf(calcPers)).setScale(2, RoundingMode.CEILING);
        }
        return new BigDecimal(BigInteger.ZERO);
    }

    public BigDecimal profitCalculation() {
        if (payoutPercentage == 0 && spent.compareTo(BigDecimal.ZERO) != 0) {
            return soldAtPrice.subtract(spent);
        } else if (spent.compareTo(BigDecimal.ZERO) == 0 && payoutPercentage != 0) {
            return soldAtPrice.subtract(payoutCurrency);
        } else if (payoutPercentage != 0 && spent.compareTo(BigDecimal.ZERO) != 0) {
            return soldAtPrice.subtract(spent).subtract(payoutCurrency);
        } else {
            return soldAtPrice;
        }
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMarketPlace(MarketPlaceEnum marketPlace) {
        this.marketPlace = marketPlace;
    }

    public void setDeliveryService(DeliveryServiceEnum deliveryService) {
        this.deliveryService = deliveryService;
    }

    public void setPaymentMethod(PaymentMethodEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setSoldAtPrice(BigDecimal soldAtPrice) {
        this.soldAtPrice = soldAtPrice;
    }

    public void setSpent(BigDecimal spent) {
        this.spent = spent;
    }

    public void setPayoutPercentage(Integer payoutPercentage) {
        this.payoutPercentage = payoutPercentage;
    }

    public void setPayoutCurrency(BigDecimal payoutCurrency) {
        this.payoutCurrency = payoutCurrency;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
