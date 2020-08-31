package org.example.jdbc;

import org.example.jdbc.Util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Start {
	public static void main(String[] args) {

		try(Connection connection = Util.getConnection()) {
			String sql = "select * from sales";
			System.out.println("Executing statement...");
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet resultSet = ps.executeQuery(sql);
			System.out.println("Retrieving data from database...");

			while (resultSet.next()){
				int id = resultSet.getInt("id");
				Date date = resultSet.getDate("date_time");
				String title = resultSet.getString("title");
				double price = resultSet.getDouble("sold_at_price");
				System.out.println("===========");
				System.out.println("id: " + id);
				System.out.println("date_time: " + date);
				System.out.println("title: " + title);
				System.out.println("sold_at_price: " + price);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Closing connection and releasing resources...");
		}
	}
}
