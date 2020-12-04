package org.saleslist.web.controller;

import org.saleslist.model.Product;
import org.saleslist.service.ProductService;
import org.saleslist.to.ProductTo;
import org.saleslist.util.ProductsUtil;
import org.saleslist.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.List;

import static org.saleslist.util.ValidationUtil.assureIdConsistent;
import static org.saleslist.util.ValidationUtil.checkNew;

@Controller
public class ProductRestController {
    private static final Logger log = LoggerFactory.getLogger(ProductRestController.class);

    private final ProductService service;

    public ProductRestController(ProductService service) {
        this.service = service;
    }

    public Product get(int id) {
        int userId = SecurityUtil.getAuthUserId();
        log.info("get product {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.getAuthUserId();
        log.info("delete product {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<ProductTo> getAll() {
        int userId = SecurityUtil.getAuthUserId();
        log.info("getAll for user {}", userId);
        return ProductsUtil.getTos(service.getAll(userId), SecurityUtil.authUserProfitPerDay());
    }

    public Product create(Product product) {
        int userId = SecurityUtil.getAuthUserId();
        checkNew(product);
        log.info("create {} for user {}", product, userId);
        return service.create(product, userId);
    }

    public void update(Product product, int id) {
        int userId = SecurityUtil.getAuthUserId();
        assureIdConsistent(product, id);
        log.info("update {} for user {}", product, userId);
        service.update(product, userId);
    }
}
