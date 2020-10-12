package org.saleslist.jdbc;

import org.saleslist.jdbc.enums.DeliveryServiceEnum;
import org.saleslist.jdbc.enums.MarketPlaceEnum;
import org.saleslist.jdbc.enums.OrderStatusEnum;
import org.saleslist.jdbc.enums.PaymentMethodEnum;
import org.saleslist.jdbc.model.Product;
import org.saleslist.jdbc.repository.JdbcProductRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Start {

	private static JdbcProductRepository jdbcProductRepository = new JdbcProductRepository();

	public static void main(String[] args) {

		System.out.println(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));

		// creating new product
//		Product product = new Product(LocalDateTime.now(), "BBB", MarketPlaceEnum.OTHER, DeliveryServiceEnum.COLLECTION_IN_PERSON, PaymentMethodEnum.OLX_DELIVERY, "my box", OrderStatusEnum.SUCCESS, 5500, 0);

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
		for (Product p : productList) {
			System.out.println(p);
		}
	}
}
