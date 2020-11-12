package org.saleslist;

import org.saleslist.model.Role;
import org.saleslist.model.User;
import org.saleslist.to.ProductTo;
import org.saleslist.web.product.ProductRestController;
import org.saleslist.web.user.AdminRestController;
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
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "bla93", "qwerty", Role.ADMIN));
            System.out.println();

            ProductRestController productController = appCtx.getBean(ProductRestController.class);
            List<ProductTo> filteredProductsWithExcess =
                    productController.getBetween(
                            LocalDate.of(2020, Month.OCTOBER, 31), LocalTime.of(9, 0),
                            LocalDate.of(2020, Month.OCTOBER, 31), LocalTime.of(15, 0));
            filteredProductsWithExcess.forEach(System.out::println);
            System.out.println();
            System.out.println(productController.getBetween(null, null, null, null));
        }
    }
}
