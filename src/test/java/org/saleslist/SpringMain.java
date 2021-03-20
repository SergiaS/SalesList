package org.saleslist;

import org.saleslist.enums.Role;
import org.saleslist.model.User;
import org.saleslist.to.ProductTo;
import org.saleslist.web.controller.ProductRestController;
import org.saleslist.web.user.AdminRestController;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class SpringMain {

	public static void main(String[] args) {
		try(GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
			appCtx.getEnvironment().setActiveProfiles(Profiles.getActiveDbProfile(), Profiles.REPOSITORY_IMPLEMENTATION);
			appCtx.load("spring/spring-app.xml", "spring/spring-db.xml");
			appCtx.refresh();

			System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
			AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
			adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));
			System.out.println();

			ProductRestController mealController = appCtx.getBean(ProductRestController.class);
			List<ProductTo> filteredMealsWithExcess =
					mealController.getBetween(
							LocalDate.of(2020, Month.JANUARY, 30), LocalTime.of(7, 0),
							LocalDate.of(2020, Month.JANUARY, 31), LocalTime.of(11, 0));
			filteredMealsWithExcess.forEach(System.out::println);
			System.out.println();
			System.out.println(mealController.getBetween(null, null, null, null));




//			JdbcProductRepository productRepository = appCtx.getBean(JdbcProductRepository.class);
//			System.out.println("   =========   ");
//			List<Product> productList = productRepository.getAll(100);
//			for (Product p : productList) {
//				System.out.println("userId null, LocalDateTime.of(" + p.getDateTime() + "), \"" + p.getTitle() + "\", MarketPlaceEnum." + p.getMarketPlace() + ", DeliveryServiceEnum." + p.getDeliveryService() + ", PaymentMethodEnum." + p.getPaymentMethod() + ", OrderStatusEnum." + p.getOrderStatus() + ", new BigDecimal(\"" + p.getSpent() + "\"), new BigDecimal(\"" + p.getSoldAtPrice() + "\"), " + p.getPayoutPercentage() + ", \"" + p.getNotes() + "\"");
//			}
		}
	}
}
