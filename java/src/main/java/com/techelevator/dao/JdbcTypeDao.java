package com.techelevator.dao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcTypeDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTypeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void associateMealWithType(int mealId, int typeId) {
        String sql = "INSERT INTO meal_type (meal_id, type_id) VALUES (?, ?);";
        jdbcTemplate.update(sql, mealId, typeId);
    }
}
