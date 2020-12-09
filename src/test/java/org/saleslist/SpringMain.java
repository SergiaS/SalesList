package org.saleslist;

import org.saleslist.model.Product;
import org.saleslist.repository.jdbc.JdbcProductRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

public class SpringMain {

	public static void main(String[] args) {
		try(ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml")) {
			System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

			JdbcProductRepository productRepository = appCtx.getBean(JdbcProductRepository.class);

			System.out.println("   =========   ");


			List<Product> productList = productRepository.getAll(100);
			for (Product p : productList) {
				System.out.println("userId null, LocalDateTime.of(" + p.getDateTime() + "), \"" + p.getTitle() + "\", MarketPlaceEnum." + p.getMarketPlace() + ", DeliveryServiceEnum." + p.getDeliveryService() + ", PaymentMethodEnum." + p.getPaymentMethod() + ", OrderStatusEnum." + p.getOrderStatus() + ", new BigDecimal(\"" + p.getSpent() + "\"), new BigDecimal(\"" + p.getSoldAtPrice() + "\"), " + p.getPayoutPercentage() + ", \"" + p.getNotes() + "\"");
			}
		}
	}
}
