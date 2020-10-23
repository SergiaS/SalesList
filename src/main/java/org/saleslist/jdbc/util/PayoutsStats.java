package org.saleslist.jdbc.util;

import org.saleslist.jdbc.model.Payout;
import org.saleslist.jdbc.repository.JdbcPayoutRepository;

import java.util.List;

public class PayoutsStats {

	private final JdbcPayoutRepository payoutRepository = new JdbcPayoutRepository();
	private final List<Payout> payoutList = payoutRepository.getAllPayouts();

	public double getTotalAmount() {
		return payoutList.stream()
				.mapToDouble(Payout::getAmount)
				.sum();
	}

	public double getCooperationsAmount() {
		return payoutList.stream()
				.filter(p -> p.getAmount() < 0)
				.mapToDouble(Payout::getAmount)
				.sum();
	}

	public double getPayoutsAmount() {
		return payoutList.stream()
				.filter(p -> p.getAmount() > 0)
				.mapToDouble(Payout::getAmount)
				.sum();
	}

	public long getQtyCooperations() {
		return payoutList.stream()
				.filter(p -> p.getAmount() < 0)
				.count();
	}

	public long getQtyPayouts() {
		return payoutList.stream()
				.filter(p -> p.getAmount() > 0)
				.count();
	}
}
