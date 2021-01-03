package org.saleslist.repository.jdbc;

import org.saleslist.Profiles;
import org.saleslist.model.Payout;
import org.saleslist.repository.PayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.saleslist.web.SecurityUtil.ADMIN_ID;

@Repository
public abstract class JdbcPayoutRepository<T> implements PayoutRepository {

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

    protected abstract T toDbDateTime(LocalDateTime ldt);

    @Repository
    @Profile(Profiles.POSTGRES_DB)
    public static class Java8JdbcPayoutRepository extends JdbcPayoutRepository<LocalDateTime> {

        public Java8JdbcPayoutRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
            super(jdbcTemplate, namedParameterJdbcTemplate);
        }

        @Override
        protected LocalDateTime toDbDateTime(LocalDateTime ldt) {
            return ldt;
        }
    }

    @Repository
    @Profile({Profiles.HSQL_DB})
    public static class TimestampJdbcProductRepository extends JdbcProductRepository<Timestamp> {

        public TimestampJdbcProductRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
            super(jdbcTemplate, namedParameterJdbcTemplate);
        }

        @Override
        protected Timestamp toDbDateTime(LocalDateTime ldt) {
            return Timestamp.valueOf(ldt);
        }
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
                .addValue("date_time", toDbDateTime(payout.getDateTime()))
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
                    "SELECT * FROM payouts WHERE date_time>=? AND date_time<? ORDER BY date_time DESC", ROW_MAPPER,
                    toDbDateTime(startDateTime), toDbDateTime(endDateTime));
        }
        return jdbcTemplate.query(
                "SELECT * FROM payouts WHERE user_id=? AND date_time>=? AND date_time<? ORDER BY date_time DESC", ROW_MAPPER,
                userId, toDbDateTime(startDateTime), toDbDateTime(endDateTime));
    }

    public List<String> getOwnersNames() {
        return jdbcTemplate.queryForList(
                "SELECT u.name FROM payouts INNER JOIN users u ON u.id = payouts.user_id ORDER BY date_time DESC",
                String.class);
    }
}
