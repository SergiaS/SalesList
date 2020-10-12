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

	private String notes;

	private OrderStatusEnum orderStatus;

	private double soldAtPrice; // including expenses
	private int payoutPercentage;

	public Product() {
	}

	public Product(LocalDateTime dateTime, String title, MarketPlaceEnum marketPlace, DeliveryServiceEnum deliveryService, PaymentMethodEnum paymentMethod, String notes, OrderStatusEnum orderStatus, double soldAtPrice, int payoutPercentage) {
		this.dateTime = dateTime.truncatedTo(ChronoUnit.MINUTES);
		this.title = title;
		this.marketPlace = marketPlace;
		this.deliveryService = deliveryService;
		this.paymentMethod = paymentMethod;
		this.notes = notes;
		this.orderStatus = orderStatus;
		this.soldAtPrice = soldAtPrice;
		this.payoutPercentage = payoutPercentage;
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public OrderStatusEnum getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatusEnum orderStatus) {
		this.orderStatus = orderStatus;
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

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", localDateTime=" + dateTime +
				", title='" + title + '\'' +
				", marketPlace=" + marketPlace +
				", deliveryService=" + deliveryService +
				", paymentMethod=" + paymentMethod +
				", notes='" + notes + '\'' +
				", orderStatus=" + orderStatus +
				", soldAtPrice=" + soldAtPrice +
				", payoutPercentage=" + payoutPercentage +
				'}';
	}
}
