package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.IngredientDto;
import com.techelevator.model.RecipeDto;
import com.techelevator.model.RecipeIngredientDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRecipeIngredientDao implements RecipeIngredientDao{
    private JdbcTemplate jdbcTemplate;

    private JdbcIngredientDao jdbcIngredientDao;

    private JdbcRecipeDao jdbcRecipeDao;

    public JdbcRecipeIngredientDao(DataSource dataSource, JdbcIngredientDao jdbcIngredientDao, JdbcRecipeDao jdbcRecipeDao) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcIngredientDao = jdbcIngredientDao;
        this.jdbcRecipeDao = jdbcRecipeDao;
    }


    /**
     * INCOMPLETE
     * takes in list of ingredientDtos
     * checks if item is already in db, if not, adds it and adds new id to the list
     * if is in db already SHOULD add id to the list
     *
     * @param ingredientDtoList
     * @return list of Integers corresponding to ids of ingredients in the db
     */
    public List<Integer> getIngredientIds(List<IngredientDto> ingredientDtoList) {
        //Gets back all ids except ones already in the system
        List<Integer> idsList = new ArrayList<>();
        for(IngredientDto currentIngredient : ingredientDtoList ){
            int checkForId = jdbcIngredientDao.getIngredientID(currentIngredient);
            if(checkForId == 0) {
                idsList.add(jdbcIngredientDao.addIngredientReturnId(currentIngredient));
            } else {
                idsList.add(checkForId);
            }
        }
        return idsList;
    }

    /**
     * takes in list of ingredientDtos,
     * turns list of Dtos into list of Ingredient objects *with ids*
     * **SHOULD NOT BE CALLED IF OBJECT IS NOT IN DB**
     *
     * @param ingredientDtoList
     * @return
     */
    public List<Ingredient> getListIngredients(List<IngredientDto> ingredientDtoList) {
        List<Ingredient> ingredientList = new ArrayList<>();
        for(IngredientDto currentIngredient : ingredientDtoList ){
            ingredientList.add(jdbcIngredientDao.getIngredient(currentIngredient));
        }
        return ingredientList;
    }

    /**
     * takes in a RecipeIngredientDto
     * checks to see if the recipe exists already if not, add to db and gets new id
     * checks each ingredient to see if it exists already, if not, add to db and get the new id
     *
     * then adds to the joiner
     * @param rID
     */
    public void addRecipeIngredientConnection(RecipeIngredientDto rID) {
        int recipeId = jdbcRecipeDao.getRecipeID(rID.getRecipe());
        if (recipeId == 0) {
            recipeId = jdbcRecipeDao.addRecipeReturnId(rID.getRecipe());
        }
        List<Integer> ingredientsIds = getIngredientIds(rID.getIngredients());
        String sql = "INSERT INTO recipe_ingredient(recipe_id, ingredient_id) VALUES (?,?)";
        for (Integer ingredientId : ingredientsIds) {
            try {
                jdbcTemplate.update(sql, recipeId, ingredientId);
            } catch (Exception e) {
                System.out.printf("%s%n%s%n%s%n%s%n",
                        "Class: " + this.getClass(),
                        "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                        "Exception: " + e,
                        "Parameters: "
                );
            }
        }
    }

    /**
     * takes an id
     * then returns the corresponding recipe and ingredients
     * as an object
     *
     * @param id
     * @return
     */
    public List<RecipeIngredientDto> getRecipeAndIngredientsFromId(int id){
        RecipeIngredientDto recipeIngredientDto = new RecipeIngredientDto();
        List<RecipeIngredientDto> recipeFormatted = new ArrayList<>();
        RecipeDto recipe = new RecipeDto();
        List<IngredientDto> ingredients = new ArrayList<>();
        String sql = "SELECT ingredient_id FROM recipe_ingredient WHERE recipe_id = ?;";
        try{
            recipe = jdbcRecipeDao.getRecipeFromId(id);
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            while(results.next()){
                IngredientDto currentIngredient = jdbcIngredientDao.getIngredientFromId(results.getInt("ingredient_id"));
                ingredients.add(currentIngredient);
            }
            recipeIngredientDto.setRecipe(recipe);
            recipeIngredientDto.setIngredients(ingredients);
            recipeFormatted.add(recipeIngredientDto);
        } catch (Exception e) {
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Parameters: "
            );
        }
        return recipeFormatted;
    }

    public List<RecipeIngredientDto> getAllRecipes(){
        List<Integer> recipeIdList = new ArrayList<>();
        List<RecipeIngredientDto> recipeIngredientDtoList = new ArrayList<>();
        String sql = "SELECT recipe_id FROM recipe";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            recipeIdList.add(results.getInt("recipe_id"));
        }
        for(Integer currentId : recipeIdList){
            recipeIngredientDtoList.add(getRecipeAndIngredientsFromId(currentId).get(0));
        }
        return recipeIngredientDtoList;
    }

    public void addRecipetoUser(int userId, int recipeId){
        String sql = "INSERT INTO recipe_users(recipe_id, user_id) VALUES (?,?);";
        try{
            jdbcTemplate.update(sql,recipeId,userId);
        } catch (Exception e) {
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Parameters: "
            );
        }
    }
    public List<RecipeIngredientDto> getRecipesByUserId(int userId){
        List<RecipeIngredientDto> recipeIngredientDtoList = new ArrayList<>();
        String sql = "SELECT recipe_id FROM recipe_users WHERE user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()){
            int recipeId = results.getInt("recipe_id");
            RecipeIngredientDto currentRecipe = getRecipeAndIngredientsFromId(recipeId).get(0);
            recipeIngredientDtoList.add(currentRecipe);
        }
        return recipeIngredientDtoList;
    }
}
