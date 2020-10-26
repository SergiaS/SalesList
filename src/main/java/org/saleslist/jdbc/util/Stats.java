package org.saleslist.jdbc.util;

import org.saleslist.jdbc.enums.OrderStatusEnum;
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

	// how much total money i spent (include ads, delivery...)
	public double getAmountOfExpenses() {
		return productList.stream()
				.mapToDouble(Product::getSpent)
				.sum();
	}

	// how much total money did i get
	public double getAmountAtSoldPrice() {
		return productList.stream()
				.mapToDouble(Product::getSoldAtPrice)
				.sum();
	}

	// how much profit did i get on each product
	public List<Double> getEachItemProfit() {
		return productList.stream()
				.map(Product::getProfit)
				.collect(Collectors.toList());
	}

	// how much total profit did i get
	public double getAmountOfProfit() {
		return productList.stream()
				.mapToDouble(Product::getProfit)
				.sum();
	}

	// how much i paid for each product
	public List<Double> getEachItemPayout() {
		return productList.stream()
				.map(p -> Double.parseDouble(doubleTemplate.format(p.getPayoutCurrency())))
				.collect(Collectors.toList());
	}

	// how much total did i pay
	public double getAmountOfPayouts() {
		return productList.stream()
				.mapToDouble(p -> Double.parseDouble(doubleTemplate.format(p.getPayoutCurrency())))
				.sum();
	}

	// how much products have i sold
	public long getNumberOfSoldItems() {
		return productList.stream()
				.filter(p -> p.getOrderStatus() == OrderStatusEnum.SUCCESS)
				.count();
	}

	// how many sold items was by cooperation
	public long getNumberOfCooperationItems() {
		return productList.stream()
				.filter(p -> p.getPayoutPercentage() > 0)
				.count();
	}

	// how many sold items was only mine
	public long getNumberOfMyItems() {
		return productList.stream()
				.filter(p -> p.getPayoutPercentage() == 0)
				.count();
	}


	private final Map<String, Long> deliveryCounterMap;
	private final Map<String, Long> marketPlaceCounterMap;
	private final Map<String, Long> paymentMethodCounterMap;
	private final Map<String, Long> orderStatusCounterMap;

	{
		deliveryCounterMap = enumCounter(productList -> productList.getDeliveryService().toString());
		marketPlaceCounterMap = enumCounter(productList -> productList.getMarketPlace().toString());
		paymentMethodCounterMap = enumCounter(productList -> productList.getPaymentMethod().toString());
		orderStatusCounterMap = enumCounter(productList -> productList.getOrderStatus().toString());
	}

	private Map<String, Long> enumCounter(Function<Product, String> f) {
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
