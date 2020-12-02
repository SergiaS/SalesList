package org.saleslist.jdbc;

import org.saleslist.jdbc.model.Payout;
import org.saleslist.jdbc.repository.JdbcPayoutRepository;
import org.saleslist.jdbc.repository.JdbcProductRepository;

import java.util.List;

public class Start {

	private static JdbcProductRepository productRepository = new JdbcProductRepository();
	private static JdbcPayoutRepository payoutRepository = new JdbcPayoutRepository();

	public static void main(String[] args) {
		// Payout functionality
//		Payout payout = new Payout();
//		payout.setProductId(28);
//		payout.setDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
//		payout.setAmount(50);
//		payout.setNotes("");
//		JdbcPayoutRepository payoutRepository = new JdbcPayoutRepository();
//		payoutRepository.add(payout);

		// creating new product
//		Product product = new Product(LocalDateTime.now(), "CCC", MarketPlaceEnum.OTHER, DeliveryServiceEnum.COLLECTION_IN_PERSON, PaymentMethodEnum.OLX_DELIVERY, OrderStatusEnum.SUCCESS, 0, 5500, 10, "my box", false);

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
//		List<Product> productList = productRepository.getAllProducts();
//		for (Product p : productList) {
//			System.out.println("(userId, '" + p.getDateTime() + "', '" + p.getTitle() + "', '" + p.getMarketPlace() + "', '" + p.getDeliveryService() + "', '" + p.getPaymentMethod() + "', '" + p.getOrderStatus() + "', " + p.getSpent() + "," + p.getSoldAtPrice() + "," + p.getPayoutPercentage() + "," + p.getPayoutCurrency() + "," + p.getProfit() + ",'" + p.getNotes() + "'),");
//		}

		// get all payouts from DB
		List<Payout> payoutList = payoutRepository.getAllPayouts();
		for (Payout payout : payoutList) {
			System.out.println(payout);
		}
	}
}
