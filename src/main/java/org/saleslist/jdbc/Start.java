package org.saleslist.jdbc;

import org.saleslist.jdbc.enums.DeliveryServiceEnum;
import org.saleslist.jdbc.enums.MarketPlaceEnum;
import org.saleslist.jdbc.enums.OrderStatusEnum;
import org.saleslist.jdbc.enums.PaymentMethodEnum;
import org.saleslist.jdbc.model.Product;
import org.saleslist.jdbc.repository.JdbcProductRepository;
import org.saleslist.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Start {
	public static void main(String[] args) {

		// creating new product
//		Product product = new Product(LocalDateTime.now(), "AAAAAAAAAAAA", MarketPlaceEnum.OTHER, DeliveryServiceEnum.COLLECTION_IN_PERSON, PaymentMethodEnum.OLX_DELIVERY, "my box", OrderStatusEnum.SUCCESS, 5500, 0);

		JdbcProductRepository jdbcProductRepository = new JdbcProductRepository();

		// save new product to DB
//		jdbcProductRepository.save(product);

		// deleting product by id
//		jdbcProductRepository.delete(6);

		// update product by id
//		jdbcProductRepository.update(7, product);

		// get product by id from DB
//		Product byId = jdbcProductRepository.getById(7);
//		System.out.println(byId);

		// get all products from DB
		List<Product> productList = jdbcProductRepository.getAllProducts();
		for (Product product : productList) {
			System.out.println(product);
		}
	}
}
