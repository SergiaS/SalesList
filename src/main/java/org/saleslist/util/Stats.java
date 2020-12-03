package org.saleslist.util;

import org.saleslist.enums.OrderStatusEnum;
import org.saleslist.model.Product;
import org.saleslist.repository.jdbc.JdbcProductRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.saleslist.web.SecurityUtil.getAuthUserId;

public class Stats {

	private final ConfigurableApplicationContext springContext;
	private final JdbcProductRepository repository;
	private final List<Product> productList;

	{
		springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
		repository = springContext.getBean(JdbcProductRepository.class);
		productList = repository.getAll(getAuthUserId());
	}

	public BigDecimal getAmountOfExpenses() {
		return productList.stream()
				.map(Product::getSpent)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getAmountAtSoldPrice() {
		return productList.stream()
				.map(Product::getSoldAtPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getAmountOfProfit() {
		return productList.stream()
				.map(Product::getProfit)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getAmountOfPayouts() {
		return productList.stream()
				.map(Product::getPayoutCurrency)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public long getNumberOfSoldItems() {
		return productList.stream()
				.filter(p -> p.getOrderStatus() == OrderStatusEnum.SUCCESS)
				.count();
	}

	public long getNumberOfCooperationItems() {
		return productList.stream()
				.filter(p -> p.getPayoutPercentage() > 0)
				.count();
	}

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
