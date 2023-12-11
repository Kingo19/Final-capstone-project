package com.techelevator.dao;

import com.techelevator.model.MealDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class JdbcMealDao implements MealDao {
    // Inject the JdbcTemplate
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





