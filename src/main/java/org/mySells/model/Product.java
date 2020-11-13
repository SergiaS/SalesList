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

    private final LocalDateTime dateTime;
    private final String title;

    private final MarketPlaceEnum marketPlace;
    private final DeliveryServiceEnum deliveryService;
    private final PaymentMethodEnum paymentMethod;
    private final OrderStatusEnum orderStatus;

    private final BigDecimal soldAtPrice;
    private final BigDecimal spent;
    private final Integer payoutPercentage;
    private final BigDecimal payoutCurrency;
    private final BigDecimal profit;

    private final String notes;

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
