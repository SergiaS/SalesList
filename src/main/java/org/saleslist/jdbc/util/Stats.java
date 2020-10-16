package org.saleslist.jdbc.util;

import org.saleslist.jdbc.model.Product;
import org.saleslist.jdbc.repository.JdbcProductRepository;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stats {

	private final JdbcProductRepository repository = new JdbcProductRepository();
	private final List<Product> productList = repository.getAllProducts();
	private final DecimalFormat df = new DecimalFormat("#.##");

	// how much total money did i get
	public double getTotalPrice() {
		return productList.stream()
				.mapToDouble(Product::getSoldAtPrice)
				.sum();
	}

	// how much profit did i get on each product
	public List<Double> getAllProfits() {
		return productList.stream()
				.map(p -> Double.valueOf(df.format(p.getSoldAtPrice() - p.getSoldAtPrice() * (p.getPayoutPercentage() / 100.0))))
				.collect(Collectors.toList());
	}

	// how much total profit did i get
	public double getTotalProfit() {
		return productList.stream()
				.mapToDouble(p -> Double.parseDouble(df.format(p.getSoldAtPrice() - p.getSoldAtPrice() * (p.getPayoutPercentage() / 100.0))))
				.sum();
	}

	// how much i paid for each product
	public List<Double> getAllPayouts() {
		return productList.stream()
				.map(p -> Double.valueOf(df.format(p.getSoldAtPrice() * (p.getPayoutPercentage() / 100.0))))
				.collect(Collectors.toList());
	}

	// how much total did i pay
	public double getTotalPayouts() {
		return productList.stream()
				.mapToDouble(p -> Double.parseDouble(df.format(p.getSoldAtPrice() * (p.getPayoutPercentage() / 100.0))))
				.sum();
	}

	// how much products have i sold
	public int getTotalPositions() {
		return productList.size();
	}


	// try refactor all Counters methods to one by generics
	private Map<String, Integer> deliveryCounterMap = new HashMap<>();
	private Map<String, Integer> marketPlaceCounterMap = new HashMap<>();
	private Map<String, Integer> paymentMethodCounterMap = new HashMap<>();
	private Map<String, Integer> orderStatusCounterMap = new HashMap<>();

	static int iStatic = 0;
	{
		counter();
		System.out.println("Stats object counter: " + ++iStatic);
	}

	private void counter() {
		for (Product product : productList) {
			String deliveryTmp = product.getDeliveryService().toString();
			deliveryCounterMap.putIfAbsent(deliveryTmp, 0);
			deliveryCounterMap.computeIfPresent(deliveryTmp, (k, v) -> v + 1);

			String marketPlaceTmp = product.getMarketPlace().toString();
			marketPlaceCounterMap.putIfAbsent(marketPlaceTmp, 0);
			marketPlaceCounterMap.computeIfPresent(marketPlaceTmp, (k, v) -> v + 1);

			String paymentMethodTmp = product.getPaymentMethod().toString();
			paymentMethodCounterMap.putIfAbsent(paymentMethodTmp, 0);
			paymentMethodCounterMap.computeIfPresent(paymentMethodTmp, (k, v) -> v + 1);

			String orderStatusTmp = product.getOrderStatus().toString();
			orderStatusCounterMap.putIfAbsent(orderStatusTmp, 0);
			orderStatusCounterMap.computeIfPresent(orderStatusTmp, (k, v) -> v + 1);
		}
	}

	public Map<String, Integer> getDeliveryCounterMap() {
		return deliveryCounterMap;
	}

	public Map<String, Integer> getMarketPlaceCounterMap() {
		return marketPlaceCounterMap;
	}

	public Map<String, Integer> getPaymentMethodCounterMap() {
		return paymentMethodCounterMap;
	}

	public Map<String, Integer> getStatusOrderCounterMap() {
		return orderStatusCounterMap;
	}
}
