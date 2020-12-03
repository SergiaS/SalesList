package org.saleslist.repository.jdbc;

import org.saleslist.model.AbstractBaseEntity;
import org.saleslist.repository.MainRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.time.LocalDateTime;
import java.util.List;

import static org.saleslist.web.SecurityUtil.ADMIN_ID;

public abstract class JdbcMainRepository<T extends AbstractBaseEntity> implements MainRepository<T> {

    protected JdbcTemplate jdbcTemplate;

    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    protected SimpleJdbcInsert simpleJdbcInsert;

    public JdbcMainRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    abstract public T save(T t, int userId);

    abstract public boolean delete(int id, int userId);

    public T get(int id, int userId) {
        List<T> listModels;
        if (userId == ADMIN_ID) {
            listModels = jdbcTemplate.query(String.format("SELECT * FROM %s WHERE id=?", getTableName()), getRowMapper(), id);
        } else {
            listModels = jdbcTemplate.query(String.format("SELECT * FROM %s WHERE id=? AND user_id=?", getTableName()), getRowMapper(), id, userId);
        }
        return DataAccessUtils.singleResult(listModels);
    }

    public List<T> getAll(int userId) {
        if (userId == ADMIN_ID) {
            return jdbcTemplate.query(String.format("SELECT * FROM %s ORDER BY date_time DESC", getTableName()), getRowMapper());
        }
        return jdbcTemplate.query(String.format("SELECT * FROM %s WHERE user_id=? ORDER BY date_time DESC", getTableName()), getRowMapper(), userId);
    }

    public List<T> getBetweenDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return jdbcTemplate.query(
                String.format("SELECT * FROM %s WHERE user_id=? AND date_time>=? AND date_time<? ORDER BY date_time DESC", getTableName()),
                getRowMapper(), userId, startDateTime, endDateTime);
    }

    abstract protected RowMapper<T> getRowMapper();

    abstract protected String getTableName();

    // only for ADMIN user
    public List<String> getOwnersNames() {
        return jdbcTemplate.queryForList(
                "SELECT u.name FROM products INNER JOIN users u ON u.id = products.user_id ORDER BY date_time DESC",
                String.class);
    }
}
