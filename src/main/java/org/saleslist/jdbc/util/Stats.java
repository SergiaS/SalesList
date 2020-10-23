package org.saleslist.jdbc.util;

import org.saleslist.jdbc.model.Product;
import org.saleslist.jdbc.repository.JdbcProductRepository;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Stats {

	private final JdbcProductRepository repository = new JdbcProductRepository();
	private final List<Product> productList = repository.getAllProducts();
	public static final DecimalFormat doubleTemplate = new DecimalFormat("#.##");

	public double getTotalSpent() {
		return productList.stream()
				.mapToDouble(Product::getSpent)
				.sum();
	}

	// how much total money did i get
	public double getTotalPrice() {
		return productList.stream()
				.mapToDouble(Product::getSoldAtPrice)
				.sum();
	}

	// how much profit did i get on each product
	public List<Double> getEachProfit() {
		return productList.stream()
				.map(Product::getProfit)
				.collect(Collectors.toList());
	}

	// how much total profit did i get
	public double getTotalProfit() {
		return productList.stream()
				.mapToDouble(Product::getProfit)
				.sum();
	}

	// how much i paid for each product
	public List<Double> getEachPayout() {
		return productList.stream()
				.map(p -> Double.valueOf(doubleTemplate.format(p.getSoldAtPrice() * (p.getPayoutPercentage() / 100.0))))
				.collect(Collectors.toList());
	}

	// how much total did i pay
	public double getTotalPayouts() {
		return productList.stream()
				.mapToDouble(p -> Double.parseDouble(doubleTemplate.format(p.getSoldAtPrice() * (p.getPayoutPercentage() / 100.0))))
				.sum();
	}

	// how much products have i sold
	public int getTotalPositions() {
		return productList.size();
	}


	private final Map<String, Long> deliveryCounterMap;
	private final Map<String, Long> marketPlaceCounterMap;
	private final Map<String, Long> paymentMethodCounterMap;
	private final Map<String, Long> orderStatusCounterMap;

	static int iStatic = 0;
	{
		deliveryCounterMap = counter(productList -> productList.getDeliveryService().toString());
		marketPlaceCounterMap = counter(productList -> productList.getMarketPlace().toString());
		paymentMethodCounterMap = counter(productList -> productList.getPaymentMethod().toString());
		orderStatusCounterMap = counter(productList -> productList.getOrderStatus().toString());
		System.out.println("Stats object connection counter: " + ++iStatic);
	}

	private Map<String, Long> counter(Function<Product, String> f) {
		return productList.stream().collect(Collectors.groupingBy(f, Collectors.counting()));
	}

	public Map<String, Long> getDeliveryCounterMap() {
		return deliveryCounterMap;
	}

	public Map<String, Long> getMarketPlaceCounterMap() {
		return marketPlaceCounterMap;
	}

	public Map<String, Long> getPaymentMethodCounterMap() {
		return paymentMethodCounterMap;
	}

	public Map<String, Long> getStatusOrderCounterMap() {
		return orderStatusCounterMap;
	}
}
