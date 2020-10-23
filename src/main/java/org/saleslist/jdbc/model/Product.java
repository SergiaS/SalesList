package org.saleslist.jdbc.model;

import org.saleslist.jdbc.enums.DeliveryServiceEnum;
import org.saleslist.jdbc.enums.MarketPlaceEnum;
import org.saleslist.jdbc.enums.OrderStatusEnum;
import org.saleslist.jdbc.enums.PaymentMethodEnum;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Product {

	private int id;
	private LocalDateTime dateTime;
	private String title;

	private MarketPlaceEnum marketPlace;
	private DeliveryServiceEnum deliveryService;
	private PaymentMethodEnum paymentMethod;
	private OrderStatusEnum orderStatus;

	private double spent;
	private double soldAtPrice;
	private int payoutPercentage;
	private double payoutCurrency;
	private double profit;

	private String notes;

	private boolean isPayoutPaid;

	public Product() {
	}

	// for reading product from db
	public Product(LocalDateTime dateTime, String title, MarketPlaceEnum marketPlace, DeliveryServiceEnum deliveryService, PaymentMethodEnum paymentMethod, OrderStatusEnum orderStatus, double spent, double soldAtPrice, int payoutPercentage, double payoutCurrency, double profit, String notes, boolean isPayoutPaid) {
		this.dateTime = dateTime.truncatedTo(ChronoUnit.MINUTES);
		this.title = title;
		this.marketPlace = marketPlace;
		this.deliveryService = deliveryService;
		this.paymentMethod = paymentMethod;
		this.orderStatus = orderStatus;
		this.spent = spent;
		this.soldAtPrice = soldAtPrice;
		this.payoutPercentage = payoutPercentage;
		this.payoutCurrency = payoutCurrency;
		this.profit = profit;
		this.notes = notes;
		this.isPayoutPaid = isPayoutPaid;
	}

	// for saving product to db
	public Product(LocalDateTime dateTime, String title, MarketPlaceEnum marketPlace, DeliveryServiceEnum deliveryService, PaymentMethodEnum paymentMethod, OrderStatusEnum orderStatus, double spent, double soldAtPrice, int payoutPercentage, String notes, boolean isPayoutPaid) {
//		this(dateTime, title, marketPlace, deliveryService, paymentMethod, orderStatus, spent, soldAtPrice, payoutPercentage, 0, 0, notes, isPayoutPaid);

		this.dateTime = dateTime.truncatedTo(ChronoUnit.MINUTES);
		this.title = title;
		this.marketPlace = marketPlace;
		this.deliveryService = deliveryService;
		this.paymentMethod = paymentMethod;
		this.orderStatus = orderStatus;
		this.spent = spent;
		this.soldAtPrice = soldAtPrice;
		this.payoutPercentage = payoutPercentage;
		this.notes = notes;
		this.isPayoutPaid = isPayoutPaid;

		this.payoutCurrency = payoutCurrencyCalculation();
		this.profit = profitCalculation();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
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

	public double getSpent() {
		return spent;
	}

	public void setSpent(double spent) {
		this.spent = spent;
	}

	public double getSoldAtPrice() {
		return soldAtPrice;
	}

	public void setSoldAtPrice(double soldAtPrice) {
		this.soldAtPrice = soldAtPrice;
	}

	public int getPayoutPercentage() {
		return payoutPercentage;
	}

	public void setPayoutPercentage(int payoutPercentage) {
		this.payoutPercentage = payoutPercentage;
	}

	public double getPayoutCurrency() {
		return payoutCurrency;
	}

	public void setPayoutCurrency(double payoutCurrency) {
		this.payoutCurrency = payoutCurrency;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isPayoutPaid() {
		return isPayoutPaid;
	}

	public void setPayoutPaid(boolean payoutPaid) {
		isPayoutPaid = payoutPaid;
	}

	public double payoutCurrencyCalculation() {
		if (payoutPercentage > 0) {
			return (soldAtPrice - spent) * (payoutPercentage / 100.0);
		}
		return 0;
	}

	public double profitCalculation() {
		if (payoutPercentage == 0 && spent != 0) {
			return soldAtPrice - spent;
		} else if (spent == 0 && payoutPercentage != 0) {
			return soldAtPrice - payoutCurrency;
		} else if (payoutPercentage != 0 && spent != 0) {
			return soldAtPrice - spent - payoutCurrency;
		} else {
			return soldAtPrice;
		}
	}

	@Override
	public String toString() {
		return "@ Product{" +
				"id=" + id +
				", dateTime=" + dateTime +
				", title='" + title + '\'' +
				", marketPlace=" + marketPlace +
				", deliveryService=" + deliveryService +
				", paymentMethod=" + paymentMethod +
				", orderStatus=" + orderStatus +
				", spent=" + spent +
				", soldAtPrice=" + soldAtPrice +
				", payoutPercentage=" + payoutPercentage +
				", payoutCurrency=" + payoutCurrency +
				", profit=" + profit +
				", notes='" + notes + '\'' +
				", isPayoutPaid=" + isPayoutPaid +
				'}';
	}
}
