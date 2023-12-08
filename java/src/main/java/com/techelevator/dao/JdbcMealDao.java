package com.techelevator.dao;

import com.techelevator.model.RecipeIngredientDto;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

//public class JdbcMealDao implements MealDao{
//
//    private JdbcTemplate jdbcTemplate;
//    private RecipeIngredientDao recipeIngredientDao;
//    public JdbcMealDao(DataSource dataSource, RecipeIngredientDao recipeIngredientDao) {
//        jdbcTemplate = new JdbcTemplate(dataSource);
//        this.recipeIngredientDao = recipeIngredientDao;
//    }
//
//    @Override
//    public void createMeal(List<RecipeIngredientDto> recipeIngredientList) {
//        String sql = ""
//    }
//}
