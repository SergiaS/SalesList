package org.saleslist.web.product;

import org.saleslist.model.Product;
import org.saleslist.service.ProductService;
import org.saleslist.to.ProductTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.saleslist.util.ProductsUtil.getFilteredTos;
import static org.saleslist.util.ProductsUtil.getTos;
import static org.saleslist.util.ValidationUtil.assureIdConsistent;
import static org.saleslist.web.SecurityUtil.*;

@Controller
public class ProductRestController {
    private static final Logger log = LoggerFactory.getLogger(ProductRestController.class);

    private final ProductService service;

    public ProductRestController(ProductService service) {
        this.service = service;
    }

    public Product get(int id) {
        int userId = authUserId();
        log.info("get product {} from user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = authUserId();
        log.info("delete product {} from user {}", id, userId);
        service.delete(id, userId);
    }

    public List<ProductTo> getAll() {
        int userId = authUserId();
        log.info("getAll products for user {} ", userId);
        return getTos(service.getAll(userId), authUserProfitsPerDay());
    }

    public Product create(Product product) {
        int userId = authUserId();
        log.info("create product {} for userId={}", product, userId);
        return service.create(product, userId);
    }

    public void update(Product product, int id) {
        int userId = authUserId();
        assureIdConsistent(product, id);
        log.info("update product={} for userId={}", product, userId);
        service.update(product, userId);
    }

    /**
     * <ol>Filter separately
     * <li>by date</li>
     * <li>by time for every date</li>
     * </ol>
     */
    public List<ProductTo> getBetween(@Nullable LocalDate startDate, @Nullable LocalTime startTime,
                                      @Nullable LocalDate endDate, @Nullable LocalTime endTime) {
        int userId = authUserId();
        log.info("getBetween dates({} - {}) time({} - {}) for user {}", startDate, endDate, startTime, endTime, userId);

        List<Product> productsDateFiltered = service.getBetweenInclusive(startDate, endDate, userId);
        return getFilteredTos(productsDateFiltered, authUserProfitsPerDay(), startTime, endTime);
    }

}
