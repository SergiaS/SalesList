package org.saleslist.repository.jdbc;

import org.saleslist.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import static org.saleslist.web.SecurityUtil.ADMIN_ID;

@Repository
public class JdbcProductRepository extends JdbcMainRepository<Product> {

    private static final RowMapper<Product> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Product.class);

    @Autowired
    public JdbcProductRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("products")
                .usingGeneratedKeyColumns("id");
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
            int newId = simpleJdbcInsert.executeAndReturnKey(map).intValue();
            product.setId(newId);
        } else {
            if (namedParameterJdbcTemplate.update(
                    "UPDATE products " +
                        "SET date_time=:date_time, title=:title, market_place=:market_place, delivery_service=:delivery_service, payment_method=:payment_method, order_status=:order_status, sold_at_price=:sold_at_price, spent=:spent, payout_percentage=:payout_percentage, payout_currency=:payout_currency, profit=:profit, notes=:notes " +
                        "WHERE id=:id AND user_id=:user_id", map) == 0) {
                return null;
            }
        }

        // for payout
        if (product.getPayoutPercentage() > 0) {
            jdbcTemplate.update("INSERT INTO payouts(user_id, product_id, date_time, amount, notes) " +
                    "VALUES (?,?,?,?,?) " +
                    "ON CONFLICT (product_id)" +
                    "DO UPDATE SET user_id=EXCLUDED.user_id, date_time=EXCLUDED.date_time, amount=EXCLUDED.amount, notes=EXCLUDED.notes", userId, product.getId(), product.getDateTime(), product.getPayoutCurrency(), product.getTitle());
        } else {
            jdbcTemplate.update("DELETE FROM payouts WHERE product_id=?", product.getId());
        }

        return product;
    }

    public boolean delete(int id, int userId) {
        if (userId == ADMIN_ID) {
            return jdbcTemplate.update("DELETE FROM products WHERE id=?", id) != 0;
        }
        return jdbcTemplate.update("DELETE FROM products WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    protected RowMapper<Product> getRowMapper() {
        return ROW_MAPPER;
    }

    @Override
    protected String getTableName() {
        return "products";
    }
}
