package org.saleslist.jdbc;

import org.saleslist.jdbc.enums.DeliveryServiceEnum;
import org.saleslist.jdbc.enums.MarketPlaceEnum;
import org.saleslist.jdbc.enums.OrderStatusEnum;
import org.saleslist.jdbc.enums.PaymentMethodEnum;
import org.saleslist.jdbc.model.Product;
import org.saleslist.jdbc.repository.JdbcProductRepository;

import java.util.List;

public class Start {

	private static JdbcProductRepository jdbcProductRepository = new JdbcProductRepository();

	public static void main(String[] args) {

		// creating new product
//		Product product = new Product(LocalDateTime.now(), "CCC", MarketPlaceEnum.OTHER, DeliveryServiceEnum.COLLECTION_IN_PERSON, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, 0, 5500, 0, "my box");

		// save new product to DB
//		jdbcProductRepository.save(product);

		// deleting product by id
//		jdbcProductRepository.delete(9);

		// update product by id
//		jdbcProductRepository.update(21, product);

		// get product by id from DB
//		Product byId = jdbcProductRepository.getProductById(23);
//		System.out.println(byId);

		// get all products from DB
		List<Product> productList = jdbcProductRepository.getAllProducts();
		for (Product p : productList) {
			System.out.println(p);
//			System.out.println(p.getId() + " | " + p.getDateTime() + " | " + p.getTitle() + " | " + p.getMarketPlace() + " | " + p.getDeliveryService() + " | " + p.getPaymentMethod() + " | " + p.getOrderStatus() + " | " + p.getPaid() + " | " + p.getSoldAtPrice() + " | " + p.getPaymentMethod() + " | " + p.getNotes());
		}
	}
}
