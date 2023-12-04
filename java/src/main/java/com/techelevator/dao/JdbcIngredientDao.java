package com.techelevator.dao;

import com.techelevator.model.IngredientDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
@Component
public class JdbcIngredientDao implements IngredientDao{
    private JdbcTemplate jdbcTemplate;
    public JdbcIngredientDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addIngredient(IngredientDto ingredientDto){
        String sql = "INSERT INTO ingredient(ingredient_name) VALUES (?);";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, ingredientDto.getIngredient_name());
    }
}
