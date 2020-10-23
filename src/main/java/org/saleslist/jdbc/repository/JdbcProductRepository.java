package org.saleslist.jdbc.repository;

import org.saleslist.jdbc.enums.DeliveryServiceEnum;
import org.saleslist.jdbc.enums.MarketPlaceEnum;
import org.saleslist.jdbc.enums.OrderStatusEnum;
import org.saleslist.jdbc.enums.PaymentMethodEnum;
import org.saleslist.jdbc.model.Product;
import org.saleslist.jdbc.util.ConnectionDB;
import org.saleslist.jdbc.util.Stats;

import java.sql.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductRepository implements ProductRepository {

	private final ConnectionDB conn = ConnectionDB.getInstance();

	@Override
	public Product save(Product product) {
		String sql = "insert into sales(date_time, title, market_place, delivery_service, payment_method, order_status, spent, sold_at_price, payout_percentage, payout_currency, profit, notes, is_payout_paid) " +
				"values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
			ps.setTimestamp(1, Timestamp.valueOf(product.getDateTime()));
			ps.setString(2, product.getTitle());
			ps.setString(3, product.getMarketPlace().name());
			ps.setString(4, product.getDeliveryService().name());
			ps.setString(5, product.getPaymentMethod().name());
			ps.setString(6, product.getOrderStatus().name());
			ps.setDouble(7, Double.parseDouble(Stats.doubleTemplate.format(product.getSpent())));
			ps.setDouble(8, Double.parseDouble(Stats.doubleTemplate.format(product.getSoldAtPrice())));
			ps.setInt(9, product.getPayoutPercentage());
			ps.setDouble(10, Double.parseDouble(Stats.doubleTemplate.format(product.getPayoutCurrency())));
			ps.setDouble(11, Double.parseDouble(Stats.doubleTemplate.format(product.getProfit())));
			ps.setString(12, product.getNotes());
			ps.setBoolean(13, product.isPayoutPaid());

			int rowsInserted = ps.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("New product added SUCCESSFULLY!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public Product getProductById(int id) {
		String sql = "select * from sales where id = ?";
		Product product = new Product();
		try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				product.setId(rs.getInt(1));
				product.setDateTime(rs.getTimestamp(2).toLocalDateTime());
				product.setTitle(rs.getString(3));
				product.setMarketPlace(MarketPlaceEnum.valueOf(rs.getString(4)));
				product.setDeliveryService(DeliveryServiceEnum.valueOf(rs.getString(5)));
				product.setPaymentMethod(PaymentMethodEnum.valueOf(rs.getString(6)));
				product.setOrderStatus(OrderStatusEnum.valueOf(rs.getString(7)));
				product.setSpent(rs.getDouble(8));
				product.setSoldAtPrice(rs.getDouble(9));
				product.setPayoutPercentage(rs.getInt(10));
				product.setPayoutCurrency(rs.getInt(11));
				product.setProfit(rs.getDouble(12));
				product.setNotes(rs.getString(13));
				product.setPayoutPaid(rs.getBoolean(14));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public Product update(int id, Product product) {
		String sql = "update sales SET date_time=?, title=?, market_place=?, delivery_service=?, payment_method=?, order_status=?, spent=?, sold_at_price=?, payout_percentage=?, payout_currency=?, profit=?, notes=?, is_payout_paid=? where id = ?";
		try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
			ps.setTimestamp(1, Timestamp.valueOf(product.getDateTime()));
			ps.setString(2, product.getTitle());
			ps.setString(3, product.getMarketPlace().name());
			ps.setString(4, product.getDeliveryService().name());
			ps.setString(5, product.getPaymentMethod().name());
			ps.setString(6, product.getOrderStatus().name());
			ps.setDouble(7, Double.parseDouble(Stats.doubleTemplate.format(product.getSpent())));
			ps.setDouble(8, Double.parseDouble(Stats.doubleTemplate.format(product.getSoldAtPrice())));
			ps.setInt(9, product.getPayoutPercentage());
			ps.setDouble(10, product.getPayoutCurrency());
			ps.setDouble(11, Double.parseDouble(Stats.doubleTemplate.format(product.getProfit())));
			ps.setString(12, product.getNotes());
			ps.setBoolean(13, product.isPayoutPaid());
			ps.setInt(14, id);

			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Product updated SUCCESSFULLY!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public boolean delete(int id) {
		String sql = "delete from sales where id = ?";
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
	public List<Product> getAllProducts() {
		String sql = "select * from sales order by date_time desc";
		List<Product> productList = new ArrayList<>();

		try (PreparedStatement ps = conn.getConnection().prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setDateTime(rs.getTimestamp(2).toLocalDateTime().truncatedTo(ChronoUnit.MINUTES));
				product.setTitle(rs.getString(3));
				product.setMarketPlace(MarketPlaceEnum.valueOf(rs.getString(4)));
				product.setDeliveryService(DeliveryServiceEnum.valueOf(rs.getString(5)));
				product.setPaymentMethod(PaymentMethodEnum.valueOf(rs.getString(6)));
				product.setOrderStatus(OrderStatusEnum.valueOf(rs.getString(7)));
				product.setSpent(rs.getDouble(8));
				product.setSoldAtPrice(rs.getDouble(9));
				product.setPayoutPercentage(rs.getInt(10));
				product.setPayoutCurrency(rs.getDouble(11));
				product.setProfit(rs.getDouble(12));
				product.setNotes(rs.getString(13));
				product.setPayoutPaid(rs.getBoolean(14));
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}
}
