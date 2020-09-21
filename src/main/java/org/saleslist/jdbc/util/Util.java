package org.saleslist.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
	private	static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/sales_list?useTimezone=true&serverTimezone=UTC";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "root";

	public static Connection getConnection() {
		Connection connection = null;
		try {
			System.out.println("Registering JDBC driver...");
			Class.forName(DB_DRIVER);
			System.out.println("Creating database connection...");
			connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			System.out.println("Connection SUCCESS!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Connection ERROR!");
		}
		return connection;
	}
}
