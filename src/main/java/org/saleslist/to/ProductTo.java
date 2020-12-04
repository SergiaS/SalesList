package org.saleslist.to;

import org.saleslist.enums.DeliveryServiceEnum;
import org.saleslist.enums.MarketPlaceEnum;
import org.saleslist.enums.OrderStatusEnum;
import org.saleslist.enums.PaymentMethodEnum;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class ProductTo {
    private final Integer id;
    private final LocalDateTime dateTime;
    private final String title;
    private final MarketPlaceEnum marketPlace;
    private final DeliveryServiceEnum deliveryService;
    private final PaymentMethodEnum paymentMethod;
    private final OrderStatusEnum orderStatus;
    private final BigDecimal soldAtPrice;
    private final BigDecimal spent;
    private final int payoutPercentage;
    private final BigDecimal payoutCurrency;
    private final BigDecimal profit;
    private final String notes;
    private final boolean profited;

    public ProductTo(Integer id, LocalDateTime dateTime, String title, MarketPlaceEnum marketPlace, DeliveryServiceEnum deliveryService, PaymentMethodEnum paymentMethod, OrderStatusEnum orderStatus, BigDecimal soldAtPrice, BigDecimal spent, int payoutPercentage, String notes, boolean profited) {
        this.id = id;
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
        this.profited = profited;

        this.payoutCurrency = payoutCurrencyCalculation();
        this.profit = profitCalculation();
    }

    public Integer getId() {
        return id;
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

    public BigDecimal getSoldAtPrice() {
        return soldAtPrice;
    }

    public BigDecimal getSpent() {
        return spent;
    }

    public int getPayoutPercentage() {
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

    public boolean isProfited() {
        return profited;
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
        return "ProductTo{" +
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
                ", profited=" + profited +
                '}';
    }
}
