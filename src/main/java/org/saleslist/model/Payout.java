package org.saleslist.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NamedQueries({
		@NamedQuery(name = Payout.DELETE, query = "DELETE FROM Payout p WHERE p.id=:id AND p.user.id=:userId"),
		@NamedQuery(name = Payout.ALL_SORTED, query = "SELECT p FROM Payout p WHERE p.user.id=:userId ORDER BY p.dateTime DESC"),
		@NamedQuery(name = Payout.GET_BETWEEN, query = "SELECT p FROM Payout p WHERE p.user.id=:userId AND p.dateTime>=:startDateTime AND p.dateTime<:endDateTime ORDER BY p.dateTime DESC"),
		@NamedQuery(name = Payout.ADMIN_DELETE, query = "DELETE FROM Payout p WHERE p.id=:id"),
		@NamedQuery(name = Payout.ADMIN_ALL_SORTED, query = "SELECT p FROM Payout p ORDER BY p.dateTime DESC"),
		@NamedQuery(name = Payout.ADMIN_GET_BETWEEN, query = "SELECT p FROM Payout p WHERE p.dateTime >= :startDateTime AND p.dateTime < :endDateTime ORDER BY p.dateTime DESC"),
		@NamedQuery(name = Payout.ADMIN_GET_OWNERS_NAMES, query = "SELECT u.name FROM Payout p INNER JOIN User u ON u.id = p.user.id ORDER BY p.dateTime DESC"),
})
@Entity
@Table(name = "payouts")
public class Payout extends AbstractBaseEntity {
	public static final String DELETE = "Payout.delete";
	public static final String ALL_SORTED = "Payout.getAll";
	public static final String GET_BETWEEN = "Payout.getBetween";
	public static final String ADMIN_DELETE = "Payout.adminDelete";
	public static final String ADMIN_ALL_SORTED = "Payout.adminGetAll";
	public static final String ADMIN_GET_BETWEEN = "Payout.adminGetBetween";
	public static final String ADMIN_GET_OWNERS_NAMES = "Payout.getOwnersNames";

	@Column(name = "date_time", nullable = false)
	private LocalDateTime dateTime;

	@Column(name = "amount", nullable = false)
	private BigDecimal amount;

	@Column(name = "notes", nullable = false)
	private String notes;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
