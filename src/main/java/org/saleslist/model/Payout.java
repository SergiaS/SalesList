package org.saleslist.jdbc.model;

import java.time.LocalDateTime;

public class Payout {

	private int id;
	private int productId;
	private LocalDateTime dateTime;
	private double amount;
	private String notes;

	public Payout() {
	}

	public Payout(LocalDateTime dateTime, double amount, String notes) {
		this.dateTime = dateTime;
		this.amount = amount;
		this.notes = notes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Payout{" +
				"id=" + id +
				", productId=" + productId +
				", dateTime=" + dateTime +
				", amount=" + amount +
				", notes='" + notes + '\'' +
				'}';
	}
}
