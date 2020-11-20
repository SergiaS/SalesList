package org.saleslist.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payout extends AbstractBaseEntity {

	private Integer userId;
	private LocalDateTime dateTime;
	private BigDecimal amount;
	private String notes;

	public Payout() {
	}

	public Payout(Integer id, Integer userId, LocalDateTime dateTime, BigDecimal amount, String notes) {
		super(id);
		this.userId = userId;
		this.dateTime = dateTime;
		this.amount = amount;
		this.notes = notes;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
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
				", userId=" + userId +
				", dateTime=" + dateTime +
				", amount=" + amount +
				", notes='" + notes + '\'' +
				'}';
	}
}
