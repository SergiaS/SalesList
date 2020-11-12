package org.saleslist.repository;

import org.saleslist.jdbc.model.Payout;

import java.util.List;

public interface PayoutRepository {

	Payout addOrUpdate(Payout payout);

	Payout add(Payout payout);

	Payout getPayoutById(int id);

	Payout update(int id, Payout payout);

	boolean delete(String column, int id);

	List<Payout> getAllPayouts();
}
