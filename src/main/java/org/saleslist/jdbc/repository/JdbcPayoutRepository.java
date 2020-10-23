package org.saleslist.jdbc.repository;

import org.saleslist.jdbc.model.Payout;
import org.saleslist.jdbc.util.ConnectionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class JdbcPayoutRepository implements PayoutRepository {

	private final ConnectionDB conn = ConnectionDB.getInstance();

	@Override
	public Payout addOrUpdate(Payout payout) {
		String sql = "insert into payouts(product_id, date_time, amount, notes) " +
				"VALUES (?,?,?,?) " +
				"ON DUPLICATE KEY UPDATE product_id=?, date_time=?, amount=?, notes=?";

		try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
			ps.setInt(1, payout.getProductId());
			ps.setTimestamp(2, Timestamp.valueOf(payout.getDateTime()));
			ps.setDouble(3, payout.getAmount());
			ps.setString(4, payout.getNotes());
			ps.setInt(5, payout.getProductId());
			ps.setTimestamp(6, Timestamp.valueOf(payout.getDateTime()));
			ps.setDouble(7, payout.getAmount());
			ps.setString(8, payout.getNotes());

			int rowsInserted = ps.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Payout added/updated SUCCESSFULLY!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return payout;
	}

	@Override
	public Payout add(Payout payout) {
		String sql = "insert into payouts(date_time, amount, notes) " +
				"VALUES (?,?,?)";

		try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
			ps.setTimestamp(1, Timestamp.valueOf(payout.getDateTime()));
			ps.setDouble(2, payout.getAmount());
			ps.setString(3, payout.getNotes());

			int rowsInserted = ps.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("New payout added SUCCESSFULLY!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return payout;
	}

	@Override
	public Payout getPayoutById(int id) {
		String sql = "select * from payouts where id = ?";

		Payout payout = new Payout();
		try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				payout.setId(rs.getInt(1));
				payout.setProductId(rs.getInt(2));
				payout.setDateTime(rs.getTimestamp(3).toLocalDateTime());
				payout.setAmount(rs.getDouble(4));
				payout.setNotes(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return payout;
	}

	@Override
	public Payout update(int id, Payout payout) {
		String sql = "update payouts SET date_time=?, amount=?, notes=? where id=?";
		try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
			ps.setTimestamp(1, Timestamp.valueOf(payout.getDateTime()));
			ps.setDouble(2, payout.getAmount());
			ps.setString(3, payout.getNotes());
			ps.setInt(4, id);

			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Product updated SUCCESSFULLY!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return payout;
	}

	@Override
	public boolean delete(String column, int id) {
		String sql = "delete from payouts where " + column + " = ?";
		int rowsStatus = 0;
		try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
			ps.setInt(1, id);
			rowsStatus = ps.executeUpdate();
			if (rowsStatus > 0) {
				System.out.println("Product was deleted successfully!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsStatus > 0;
	}

	@Override
	public List<Payout> getAllPayouts() {
		String sql = "select * from payouts order by date_time desc";
		List<Payout> payoutList = new ArrayList<>();
		try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Payout payout = new Payout();
				payout.setId(rs.getInt(1));
				payout.setProductId(rs.getInt(2));
				payout.setDateTime(rs.getTimestamp(3).toLocalDateTime());
				payout.setAmount(rs.getDouble(4));
				payout.setNotes(rs.getString(5));
				payoutList.add(payout);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return payoutList;
	}

	public int getLastProductIdFromDb() {
		String sql = "select id from sales order by id desc limit 1";

		int productId = 0;
		try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				productId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productId;
	}
}
