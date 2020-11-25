package org.saleslist.repository.jdbc;

import org.saleslist.model.Product;
import org.saleslist.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static org.saleslist.web.SecurityUtil.ADMIN_ID;

@Repository
public class JdbcProductRepository implements ProductRepository {

    private static final RowMapper<Product> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Product.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertProduct;

    @Autowired
    public JdbcProductRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertProduct = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("products")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Product save(Product product, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", product.getId())
                .addValue("date_time", product.getDateTime())
                .addValue("title", product.getTitle())
                .addValue("market_place", product.getMarketPlace().toString())
                .addValue("delivery_service", product.getDeliveryService().toString())
                .addValue("payment_method", product.getPaymentMethod().toString())
                .addValue("order_status", product.getOrderStatus().toString())
                .addValue("sold_at_price", product.getSoldAtPrice())
                .addValue("spent", product.getSpent())
                .addValue("payout_percentage", product.getPayoutPercentage())
                .addValue("payout_currency", product.getPayoutCurrency())
                .addValue("profit", product.getProfit())
                .addValue("notes", product.getNotes())
                .addValue("user_id", userId);

        if (product.isNew()) {
            Number newId = insertProduct.executeAndReturnKey(map);
            product.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update(
                    "UPDATE products " +
                           "SET date_time=:date_time, title=:title, market_place=:market_place, delivery_service=:delivery_service, payment_method=:payment_method, order_status=:order_status, sold_at_price=:sold_at_price, spent=:spent, payout_percentage=:payout_percentage, payout_currency=:payout_currency, profit=:profit, notes=:notes " +
                         "WHERE id=:id AND user_id=:user_id", map) == 0) {
                return null;
            }
        }
        return product;
    }

    @Override
    public boolean delete(int id, int userId) {
        if (userId == ADMIN_ID) {
            return jdbcTemplate.update("DELETE FROM products WHERE id=?", id) != 0;
        }
        return jdbcTemplate.update("DELETE FROM products WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    public Product get(int id, int userId) {
        List<Product> products;
        if (userId == ADMIN_ID) {
            products = jdbcTemplate.query("SELECT * FROM products WHERE id=?", ROW_MAPPER, id);
        } else {
            products = jdbcTemplate.query("SELECT * FROM products WHERE id=? AND user_id=?", ROW_MAPPER, id, userId);
        }
        return DataAccessUtils.singleResult(products);
    }

    @Override
    public List<Product> getAll(int userId) {
        if (userId == ADMIN_ID) {
            return jdbcTemplate.query("SELECT * FROM products ORDER BY date_time DESC", ROW_MAPPER);
        }
        return jdbcTemplate.query("SELECT * FROM products WHERE user_id=? ORDER BY date_time DESC", ROW_MAPPER, userId);
    }

    @Override
    public List<Product> getBetweenDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM products WHERE user_id=?  AND date_time>=? AND date_time<? ORDER BY date_time DESC",
                ROW_MAPPER, userId, startDateTime, endDateTime);
    }

    // only for ADMIN user
    public List<String> getOwnersNames() {
        return jdbcTemplate.queryForList("SELECT u.name FROM products INNER JOIN users u ON u.id = products.user_id ORDER BY date_time DESC", String.class);
    }
}
