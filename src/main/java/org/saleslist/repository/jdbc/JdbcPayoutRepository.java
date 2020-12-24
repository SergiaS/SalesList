package org.saleslist.repository.jdbc;

import org.saleslist.model.Payout;
import org.saleslist.repository.PayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.saleslist.web.SecurityUtil.ADMIN_ID;

@Repository
public class JdbcPayoutRepository implements PayoutRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private static final RowMapper<Payout> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Payout.class);

    @Autowired
    public JdbcPayoutRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("payouts")
                .usingGeneratedKeyColumns("id");
    }

    public Payout save(Payout payout, int userId) {
        BigDecimal payoutAmount;
        if (payout.getId() == null) {
            payoutAmount = new BigDecimal("-" + payout.getAmount());
        } else {
            payoutAmount = payout.getAmount();
        }

        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", payout.getId())
                .addValue("date_time", payout.getDateTime())
                .addValue("amount", payoutAmount)
                .addValue("notes", payout.getNotes())
                .addValue("user_id", userId);

        if (payout.isNew()) {
            Number newId = simpleJdbcInsert.executeAndReturnKey(map);
            payout.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update(
                    "UPDATE payouts " +
                           "SET date_time=:date_time, amount=:amount, notes=:notes " +
                         "WHERE id=:id AND user_id=:user_id", map) == 0)
            return null;
        }
        return payout;
    }

    public boolean delete(int id, int userId) {
        if (userId == ADMIN_ID) {
            return jdbcTemplate.update("DELETE FROM payouts WHERE id=?", id) != 0;
        }
        return jdbcTemplate.update("DELETE FROM payouts WHERE id=? AND user_id=?", id, userId) != 0;
    }

    public Payout get(int id, int userId) {
        List<Payout> payouts;
        if (userId == ADMIN_ID) {
            payouts = jdbcTemplate.query("SELECT * FROM payouts WHERE id=?", ROW_MAPPER, id);
        } else {
            payouts = jdbcTemplate.query("SELECT * FROM payouts WHERE id=? AND user_id=?", ROW_MAPPER, id, userId);
        }
        return DataAccessUtils.singleResult(payouts);
    }

    public List<Payout> getAll(int userId) {
        if (userId == ADMIN_ID) {
            return jdbcTemplate.query("SELECT * FROM payouts ORDER BY date_time DESC", ROW_MAPPER);
        }
        return jdbcTemplate.query("SELECT * FROM payouts WHERE user_id=? ORDER BY date_time DESC", ROW_MAPPER, userId);
    }

    public List<Payout> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        if (userId == ADMIN_ID) {
            return jdbcTemplate.query(
                    "SELECT * FROM payouts WHERE date_time>=? AND date_time<? ORDER BY date_time DESC", ROW_MAPPER, startDateTime, endDateTime);
        }
        return jdbcTemplate.query(
                "SELECT * FROM payouts WHERE user_id=? AND date_time>=? AND date_time<? ORDER BY date_time DESC", ROW_MAPPER, userId, startDateTime, endDateTime);
    }

    public List<String> getOwnersNames() {
        return jdbcTemplate.queryForList(
                "SELECT u.name FROM payouts INNER JOIN users u ON u.id = payouts.user_id ORDER BY date_time DESC",
                String.class);
    }
}
