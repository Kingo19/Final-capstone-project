package com.techelevator.dao;

import com.techelevator.model.Meal;
import com.techelevator.model.RecipeIngredientDto;
import com.techelevator.model.MealDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class JdbcMealDao implements MealDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcMealDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createMeal(MealDto meal) {
        String sql = "INSERT INTO meal (meal_name) VALUES (?) RETURNING meal_id;";
        return jdbcTemplate.queryForObject(sql, new Object[]{meal.getMealName()}, Integer.class);
    }


}

