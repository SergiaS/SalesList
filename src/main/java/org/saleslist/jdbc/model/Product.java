package org.saleslist.jdbc.model;

import org.saleslist.jdbc.enums.DeliveryService;
import org.saleslist.jdbc.enums.MarketPlace;
import org.saleslist.jdbc.enums.OrderStatus;
import org.saleslist.jdbc.enums.PaymentMethod;

import java.time.LocalDateTime;

public class Product {

	private int id;
	private LocalDateTime localDateTime;
	private String title;
	private MarketPlace marketPlace;
	private DeliveryService deliveryService;
	private PaymentMethod paymentMethod;
	private boolean isMyPacking;
	private boolean isOwn;
	private String notes;
	private OrderStatus orderStatus;
	private double soldAtPrice;
	private double soldIncludingExpenses;
	private double payoutForCooperation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MarketPlace getMarketPlace() {
		return marketPlace;
	}

	public void setMarketPlace(MarketPlace marketPlace) {
		this.marketPlace = marketPlace;
	}

	public DeliveryService getDeliveryService() {
		return deliveryService;
	}

	public void setDeliveryService(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public boolean isMyPacking() {
		return isMyPacking;
	}

	public void setMyPacking(boolean myPacking) {
		isMyPacking = myPacking;
	}

	public boolean isOwn() {
		return isOwn;
	}

	public void setOwn(boolean own) {
		isOwn = own;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public double getSoldAtPrice() {
		return soldAtPrice;
	}

	public void setSoldAtPrice(double soldAtPrice) {
		this.soldAtPrice = soldAtPrice;
	}

	public double getSoldIncludingExpenses() {
		return soldIncludingExpenses;
	}

	public void setSoldIncludingExpenses(double soldIncludingExpenses) {
		this.soldIncludingExpenses = soldIncludingExpenses;
	}

	public double getPayoutForCooperation() {
		return payoutForCooperation;
	}

	public void setPayoutForCooperation(double payoutForCooperation) {
		this.payoutForCooperation = payoutForCooperation;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", localDateTime=" + localDateTime +
				", title='" + title + '\'' +
				", marketPlace=" + marketPlace +
				", deliveryService=" + deliveryService +
				", paymentMethod=" + paymentMethod +
				", isMyPacking=" + isMyPacking +
				", isOwn=" + isOwn +
				", notes='" + notes + '\'' +
				", orderStatus=" + orderStatus +
				", soldAtPrice=" + soldAtPrice +
				", soldIncludingExpenses=" + soldIncludingExpenses +
				", payoutForCooperation=" + payoutForCooperation +
				'}';
	}
}
