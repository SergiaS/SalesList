package org.saleslist.repository.jdbc;

import org.saleslist.model.Payout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

import static org.saleslist.web.SecurityUtil.ADMIN_ID;

@Repository
public class JdbcPayoutRepository extends JdbcMainRepository<Payout> {

    private static final RowMapper<Payout> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Payout.class);

    @Autowired
    public JdbcPayoutRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
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

    @Override
    protected RowMapper<Payout> getRowMapper() {
        return ROW_MAPPER;
    }

    @Override
    protected String getTableName() {
        return "payouts";
    }
}
