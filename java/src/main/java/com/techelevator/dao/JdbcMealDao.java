package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Meal;
import com.techelevator.model.RecipeIngredientDto;
import com.techelevator.model.MealDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ErrorManager;


@Component
public class JdbcMealDao implements MealDao {
    private final JdbcTemplate jdbcTemplate;
    private final JdbcRecipeIngredientDao jdbcRecipeIngredientDao;
    private final JdbcRecipeDao jdbcRecipeDao;
    private final String SQL_BEGIN = "BEGIN;";
    private final String SQL_ROLLBACK = "ROLLBACK;";
    private final String SQL_COMMIT = "COMMIT;";

    public JdbcMealDao(JdbcTemplate jdbcTemplate, JdbcRecipeIngredientDao jdbcRecipeIngredientDao, JdbcRecipeDao jdbcRecipeDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcRecipeIngredientDao = jdbcRecipeIngredientDao;
        this.jdbcRecipeDao = jdbcRecipeDao;
    }

    @Override
    public Meal createMeal(MealDto mealDto, int userId) {
        Meal meal = mealDtoToMeal(mealDto);
        String sql = "INSERT INTO meal(meal_name) VALUES (?) RETURNING meal_id;";
        try{
            jdbcTemplate.update(SQL_BEGIN);
            int mealId = jdbcTemplate.queryForObject(sql, int.class, meal.getMealName());
            meal.setMealId(mealId);
            insertIntoMealType(meal);
            insertIntoMealRecipe(meal);
            insertIntoUserMeal(meal, userId);
            jdbcTemplate.update(SQL_COMMIT);
        } catch (Exception e){
            jdbcTemplate.update(SQL_ROLLBACK);
            throw e;
        }
        return meal;
    }

    public Meal mealDtoToMeal(MealDto mealDto){
        System.out.println(mealDto);
        Meal meal = new Meal();
        List<RecipeIngredientDto> recipeList = new ArrayList<>();
        for(String currentName : mealDto.getRecipeNames()){
            recipeList.add(jdbcRecipeIngredientDao.getRecipeAndIngredientsFromName(currentName).get(0));
        }
        meal.setMealName(mealDto.getMealName());
        meal.setRecipeInfo(recipeList);
        meal.setType(mealDto.getType());
        return meal;
    }
    public void insertIntoMealType(Meal meal){
        String sql = "INSERT INTO meal_type(meal_id,type_id) VALUES (?,?);";
        int typeId = getTypeIdByName(meal.getType());

        jdbcTemplate.update(sql,meal.getMealId(), typeId);
    }
    public int getTypeIdByName(String typeName){
        String sql = "SELECT type_id FROM type WHERE type_name = ?;";
        int typeId = 0;
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, typeName);
        while(result.next()){
            typeId = result.getInt("type_id");
        }
        if(typeId == 0){
            throw new DaoException("Type does not exist.");
        }
        return typeId;
    }
    public void insertIntoMealRecipe(Meal meal){
        String sql = "INSERT INTO meal_recipe(meal_id,recipe_id) VALUES (?,?);";
        for(RecipeIngredientDto currentRecipe : meal.getRecipeInfo()){
            int recipeId = jdbcRecipeDao.getRecipeID(currentRecipe.getRecipe());
            jdbcTemplate.update(sql, meal.getMealId(), recipeId);
        }
    }
    public void insertIntoUserMeal(Meal meal, int userId){
        String sql = "INSERT INTO user_meal(user_id,meal_id) VALUES (?,?);";
        jdbcTemplate.update(sql,userId,meal.getMealId());
    }

    public Meal getMealByMealID(int mealId, int userId){
        Meal meal = new Meal();
        List<RecipeIngredientDto> recipeList = new ArrayList<>();
        String sql = "SELECT meal_name,recipe_id,type_name FROM meal\n" +
                "JOIN meal_recipe ON meal_recipe.meal_id = meal.meal_id\n" +
                "JOIN meal_type ON meal_type.meal_id = meal.meal_id\n" +
                "JOIN \"type\" ON \"type\".type_id = meal_type.type_id\n" +
                "JOIN user_meal ON user_meal.meal_id = meal.meal_id\n" +
                "WHERE meal.meal_id = ? AND user_meal.user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql,mealId, userId);
        while(result.next()){
            meal.setMealId(mealId);
            meal.setType(result.getString("type_name"));
            meal.setMealName(result.getString("meal_name"));
            recipeList.add(jdbcRecipeIngredientDao.getRecipeAndIngredientsFromId(result.getInt("recipe_id")).get(0));
        }
        meal.setRecipeInfo(recipeList);
        return meal;
    }
    public List<Meal> getAllUserMeals(int userId){
        List<Meal> mealList = new ArrayList<>();
        List<Integer> idList = new ArrayList<>();
        String sql = "SELECT meal.meal_id FROM meal\n" +
                "JOIN user_meal ON user_meal.meal_id = meal.meal_id\n" +
                "WHERE user_id = ?;";
        try{
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
            while(result.next()){
                idList.add(result.getInt("meal_id"));
            }
            for(int currentId : idList){
                mealList.add(getMealByMealID(currentId,userId));
            }
        } catch (Exception e){
            System.out.println("Error, " + e);
        }
        return mealList;
    }


    @Override
    public Meal createMealSVersion(MealDto mealDto, int userId) {
        Meal meal = mealDtoToMealSVersion(mealDto);
        String sqlMeal = "INSERT INTO meal(meal_name) VALUES (?) RETURNING meal_id;";
        try {
            int mealId = jdbcTemplate.queryForObject(sqlMeal, Integer.class, meal.getMealName());
            meal.setMealId(mealId);
            insertIntoMealType(meal);
            insertIntoMealRecipe(meal);
            insertIntoUserMeal(meal, userId);
        } catch (Exception e) {
            // Handle exceptions and log errors
            throw new DaoException("Error creating meal", e);
        }
        return meal;
    }

    private Meal mealDtoToMealSVersion(MealDto mealDto) {
        System.out.println(mealDto);
        Meal meal = new Meal();
        meal.setMealName(mealDto.getMealName());
        meal.setType(mealDto.getType());
        List<RecipeIngredientDto> recipeDtos = new ArrayList<>();
        for(String recipeName : mealDto.getRecipeNames()) {
            List<RecipeIngredientDto> recipeList = jdbcRecipeIngredientDao.getRecipeAndIngredientsFromName(recipeName);
                for(RecipeIngredientDto each: recipeList){
                    if (each != null) {
                        recipeDtos.add(each);
                    }
                }
        }
        meal.setRecipeInfo(recipeDtos);
        return meal;
    }
}

