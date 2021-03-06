package org.saleslist.repository;

import org.saleslist.model.Payout;

import java.time.LocalDateTime;
import java.util.List;

public interface  PayoutRepository {

	Payout save(Payout payout, int userId);

	boolean delete(int id, int userId);

	Payout get(int id, int userId);

	List<Payout> getAll(int userId);

	List<Payout> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

	default Payout getWithUser(int id, int userId) {
		throw new UnsupportedOperationException();
	}

	List<String> getOwnersNames();
}
