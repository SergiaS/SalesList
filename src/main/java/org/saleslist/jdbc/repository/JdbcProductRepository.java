package org.saleslist.jdbc.repository;

import org.saleslist.jdbc.enums.DeliveryServiceEnum;
import org.saleslist.jdbc.enums.MarketPlaceEnum;
import org.saleslist.jdbc.enums.OrderStatusEnum;
import org.saleslist.jdbc.enums.PaymentMethodEnum;
import org.saleslist.jdbc.model.Product;
import org.saleslist.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductRepository implements ProductRepository {

	@Override
	public Product save(Product product) {

		String sql = "insert into sales(date_time, title, market_place, delivery_service, payment_method, notes, order_status, sold_at_price, payout_percentage) " +
				"values (?,?,?,?,?,?,?,?,?)";
		try (Connection connection = Util.getConnection();
		     PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setTimestamp(1, Timestamp.valueOf(product.getLocalDateTime()));
			ps.setString(2, product.getTitle());
			ps.setString(3, product.getMarketPlace().name());
			ps.setString(4, product.getDeliveryService().name());
			ps.setString(5, product.getPaymentMethod().name());
			ps.setString(6, product.getNotes());
			ps.setString(7, product.getOrderStatus().name());
			ps.setDouble(8, product.getSoldAtPrice());
			ps.setDouble(9, product.getPayoutPercentage());

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
	public Product getById(int id) {
		String sql = "select * from sales where id = ?";
		Product product = new Product();
		try (Connection connection = Util.getConnection();
		     PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				product.setId(rs.getInt(1));
				product.setLocalDateTime(rs.getTimestamp(2).toLocalDateTime());
				product.setTitle(rs.getString(3));
				product.setMarketPlace(MarketPlaceEnum.valueOf(rs.getString(4)));
				product.setDeliveryService(DeliveryServiceEnum.valueOf(rs.getString(5)));
				product.setPaymentMethod(PaymentMethodEnum.valueOf(rs.getString(6)));
				product.setNotes(rs.getString(7));
				product.setOrderStatus(OrderStatusEnum.valueOf(rs.getString(8)));
				product.setSoldAtPrice(rs.getDouble(9));
				product.setPayoutPercentage(rs.getDouble(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return product;
	}

	@Override
	public Product update(int id, Product product) {
		String sql = "update sales SET title=?, market_place=?, delivery_service=?, payment_method=?, notes=?, order_status=?, sold_at_price=?, payout_percentage=? where id = ?";
		try (Connection connection = Util.getConnection();
		     PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, product.getTitle());
			ps.setString(2, product.getMarketPlace().name());
			ps.setString(3, product.getDeliveryService().name());
			ps.setString(4, product.getPaymentMethod().name());
			ps.setString(5, product.getNotes());
			ps.setString(6, product.getOrderStatus().name());
			ps.setDouble(7, product.getSoldAtPrice());
			ps.setDouble(8, product.getPayoutPercentage());
			ps.setInt(9, id);

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
		try (Connection connection = Util.getConnection();
		     PreparedStatement ps = connection.prepareStatement(sql)) {
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
		List<Product> productList = new ArrayList<>();
		String sql = "select * from sales";

		try (Connection connection = Util.getConnection();
		     PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setLocalDateTime(rs.getTimestamp(2).toLocalDateTime());
				product.setTitle(rs.getString(3));
				product.setMarketPlace(MarketPlaceEnum.valueOf(rs.getString(4)));
				product.setDeliveryService(DeliveryServiceEnum.valueOf(rs.getString(5)));
				product.setPaymentMethod(PaymentMethodEnum.valueOf(rs.getString(6)));
				product.setNotes(rs.getString(7));
				product.setOrderStatus(OrderStatusEnum.valueOf(rs.getString(8)));
				product.setSoldAtPrice(rs.getDouble(9));
				product.setPayoutPercentage(rs.getDouble(10));
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}
}
