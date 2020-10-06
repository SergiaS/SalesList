package org.saleslist.jdbc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class ConnectionDB {

	private	static final String DB_DRIVER;
	private static final String DB_URL;
	private static final String DB_USERNAME;
	private static final String DB_PASSWORD;
	private static int numberOfConnections = 0;

	private static ConnectionDB instance;
	private Connection connection;

	static {
		Properties properties = dbProperties();
		DB_DRIVER = properties.getProperty("driver");
		DB_URL = properties.getProperty("url");
		DB_USERNAME = properties.getProperty("username");
		DB_PASSWORD = properties.getProperty("password");
	}

	public ConnectionDB() {

		try {
			System.out.println("Registering JDBC driver...");
			Class.forName(DB_DRIVER);
			System.out.println("Creating database connection...");
			connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			System.out.println("Connection SUCCESS!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Connection ERROR!");
		} finally {
			numberOfConnections++;
			System.out.println(" >>> Number of connections: " + numberOfConnections);
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static ConnectionDB getInstance() {
		if (instance == null) {
			instance = new ConnectionDB();
		} else {
			try {
				if (instance.getConnection().isClosed()) {
					instance = new ConnectionDB();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	private static Properties dbProperties() {
		String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("db/database.properties")).getPath();
		InputStream input = null;
		Properties properties = new Properties();
		try {
			input = new FileInputStream(rootPath);
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}
