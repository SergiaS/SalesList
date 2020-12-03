package org.saleslist.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payout extends AbstractBaseEntity {
	private LocalDateTime dateTime;
	private BigDecimal amount;
	private String notes;

	public Payout() {
	}

	public Payout(LocalDateTime dateTime, BigDecimal amount, String notes) {
		this(null, dateTime, amount, notes);
	}

	public Payout(Integer id, LocalDateTime dateTime, BigDecimal amount, String notes) {
		super(id);
		this.dateTime = dateTime;
		this.amount = amount;
		this.notes = notes;
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
				", dateTime=" + dateTime +
				", amount=" + amount +
				", notes='" + notes + '\'' +
				'}';
	}
}
