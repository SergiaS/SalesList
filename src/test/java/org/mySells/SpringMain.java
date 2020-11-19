package org.mySells;

import org.mySells.model.Role;
import org.mySells.model.User;
import org.mySells.to.ProductTo;
import org.mySells.web.product.ProductRestController;
import org.mySells.web.user.AdminRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/inmemory.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "Admin", "password", Role.ADMIN));
            System.out.println();

            ProductRestController productController = appCtx.getBean(ProductRestController.class);
            List<ProductTo> filteredProductsWithExcess =
                    productController.getBetween(
                            LocalDate.of(2020, Month.OCTOBER, 30), LocalTime.of(9, 0),
                            LocalDate.of(2020, Month.OCTOBER, 31), LocalTime.of(15, 0));
            filteredProductsWithExcess.forEach(System.out::println);
            System.out.println();
            System.out.println(productController.getBetween(null, null, null, null));
        }
    }
}
