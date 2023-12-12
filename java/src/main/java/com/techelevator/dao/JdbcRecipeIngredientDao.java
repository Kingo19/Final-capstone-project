package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class JdbcRecipeIngredientDao implements RecipeIngredientDao {
    private JdbcTemplate jdbcTemplate;

    private JdbcIngredientDao jdbcIngredientDao;

    private JdbcRecipeDao jdbcRecipeDao;

    public JdbcRecipeIngredientDao(DataSource dataSource,
                                   JdbcIngredientDao jdbcIngredientDao,
                                   JdbcRecipeDao jdbcRecipeDao) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcIngredientDao = jdbcIngredientDao;
        this.jdbcRecipeDao = jdbcRecipeDao;
    }

    // Helper method to log errors

    /**
     * Logs an error with details about the class, method, exception, and parameters involved.
     *
     * @param methodName Name of the method where the error occurred.
     * @param e          The exception thrown.
     * @param params     Parameters of the method where the error occurred.
     */
    private void logError(String methodName, Exception e, Object... params) {
        System.out.printf("%s%n%s%n%s%n%s%n",
                "Class: " + this.getClass(),
                "Method: " + methodName,
                "Exception: " + e,
                "Parameters: " + Arrays.toString(params)
        );
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
        for (IngredientDto currentIngredient : ingredientDtoList) {
            int checkForId = jdbcIngredientDao.getIngredientID(currentIngredient);
            if (checkForId == 0) {
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
        for (IngredientDto currentIngredient : ingredientDtoList) {
            ingredientList.add(jdbcIngredientDao.getIngredient(currentIngredient));
        }
        return ingredientList;
    }

    /**
     * takes in a RecipeIngredientDto
     * checks to see if the recipe exists already if not, add to db and gets new id
     * checks each ingredient to see if it exists already, if not, add to db and get the new id
     * <p>
     * then adds to the joiner
     *
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
     * @param recipeId
     * @return
     */
    public List<RecipeIngredientDto> getRecipeAndIngredientsFromId(int recipeId) {
        RecipeIngredientDto recipeIngredientDto = new RecipeIngredientDto();
        List<RecipeIngredientDto> recipeFormatted = new ArrayList<>();
        RecipeDto recipe = new RecipeDto();
        List<IngredientDto> ingredients = new ArrayList<>();
        String sql = "SELECT ingredient_id FROM recipe_ingredient WHERE recipe_id = ?;";
        try {
            recipe = jdbcRecipeDao.getRecipeFromId(recipeId);
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recipeId);
            while (results.next()) {
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

    public List<RecipeIngredientDto> getRecipeAndIngredientsFromName(String recipeName) {
        RecipeIngredientDto recipeIngredientDto = new RecipeIngredientDto();
        List<RecipeIngredientDto> recipeFormatted = new ArrayList<>();
        RecipeDto recipe = new RecipeDto();
        List<IngredientDto> ingredients = new ArrayList<>();
        String sql = "SELECT recipe_instructions, ingredient_id FROM recipe\n" +
                "JOIN recipe_ingredient ON recipe.recipe_id = recipe_ingredient.recipe_id\n" +
                "WHERE recipe.recipe_name = ?;";
        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, recipeName);
            while (result.next()) {
                recipe.setRecipe_name(recipeName);
                recipe.setRecipe_instructions(result.getString("recipe_instructions"));
                ingredients.add(jdbcIngredientDao.getIngredientFromId(result.getInt("ingredient_id")));
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

    public List<RecipeIngredientDto> getAllRecipes() {
        List<Integer> recipeIdList = new ArrayList<>();
        List<RecipeIngredientDto> recipeIngredientDtoList = new ArrayList<>();
        String sql = "SELECT recipe_id FROM recipe";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            recipeIdList.add(results.getInt("recipe_id"));
        }
        for (Integer currentId : recipeIdList) {
            recipeIngredientDtoList.add(getRecipeAndIngredientsFromId(currentId).get(0));
        }
        return recipeIngredientDtoList;
    }

    public void addRecipeToUser(int userId, int recipeId) {
        String sql = "INSERT INTO recipe_users(recipe_id, user_id) VALUES (?,?);";
        try {
            jdbcTemplate.update(sql, recipeId, userId);
        } catch (Exception e) {
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Parameters: "
            );
        }
    }

    public List<RecipeIngredientDto> getRecipesByUserId(int userId) {
        List<RecipeIngredientDto> recipeIngredientDtoList = new ArrayList<>();
        String sql = "SELECT recipe_id FROM recipe_users WHERE user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            int recipeId = results.getInt("recipe_id");
            RecipeIngredientDto currentRecipe = getRecipeAndIngredientsFromId(recipeId).get(0);
            recipeIngredientDtoList.add(currentRecipe);
        }
        return recipeIngredientDtoList;
    }

    public String updateRecipeAndIngredient(RecipeIngredientDto recipeIngredientDto, String recipeName) {
        List<IngredientDto> ingredientList = recipeIngredientDto.getIngredients();
        String recipeNewName = recipeIngredientDto.getRecipe().getRecipe_name();
        String recipeInstruction = recipeIngredientDto.getRecipe().getRecipe_instructions();
        String sql = "UPDATE recipe SET recipe_name = ?, recipe_instructions = ? WHERE recipe_name = ?;";
        RecipeDto newRecipeWithName = new RecipeDto();
        newRecipeWithName.setRecipe_name(recipeName);

        try {
            int recipeId = jdbcRecipeDao.getRecipeID(newRecipeWithName);
            deletePreviousIngredients(recipeId);
            jdbcTemplate.update(sql, recipeNewName, recipeInstruction, recipeName);
            recipeId = jdbcRecipeDao.getRecipeID(recipeIngredientDto.getRecipe());
            addToRecipeIngredient(ingredientList, recipeId);
        } catch (Exception e) {
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Parameters: "
            );
            return "Failure.";
        }
        return "Success.";
    }

    public void deletePreviousIngredients(int recipeId) {
        String sql = "DELETE FROM recipe_ingredient WHERE recipe_id = ?;";
        jdbcTemplate.update(sql, recipeId);
    }

    public void addToRecipeIngredient(List<IngredientDto> ingredientList, int recipeId) {
        List<Integer> ingredientsIds = getIngredientIds(ingredientList);
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

    //Added due to other method not working for getting recipe_names by user

    public List<String> getUserRecipeNames(int userId) {
        List<String> recipeNames = new ArrayList<>();
        String sql = "SELECT recipe_name FROM recipe\n" +
                "join recipe_users as ru on ru.recipe_id = recipe.recipe_id\n" +
                "WHERE user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            recipeNames.add(results.getString("recipe_name"));
        }
        return recipeNames;
    }
}

//
////===============================================Steven's method's
//
////    public RecipeIngredientDto getRecipeAndIngredientsFromNameSVersion(String recipeName) {
////        Recipe recipe = getRecipeByName(recipeName);
////        if (recipe == null) {
////            return null;
////        }
////
////        List<Ingredient> ingredients = getIngredientsByRecipeId(recipe.getRecipeId());
////        List<IngredientDto> ingredientDtos = new ArrayList<>();
////        for(Ingredient each : ingredients){
////            IngredientDto newDto = jdbcIngredientDao.getIngredientFromId(each.getId());
////            ingredientDtos.add(newDto);
////        }
////        return new RecipeIngredientDto(recipe, ingredientDtos);
////    }
//
//    private Recipe getRecipeByName(String recipeName) {
//        String sql = "SELECT recipe_id, recipe_name, recipe_instructions FROM recipe WHERE recipe_name = ?;";
//        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, recipeName);
//        if (rowSet.next()) {
//            return mapRowToRecipe(rowSet);
//        }
//        return null;
//    }
//
//    private Recipe mapRowToRecipe(SqlRowSet rowSet) {
//        Recipe recipe = new Recipe(
//                rowSet.getInt("recipe_id"),
//                rowSet.getString("recipe_name"),
//                rowSet.getString("recipe_instructions")
//                );
//        return recipe;
//    }
//
//    private List<Ingredient> getIngredientsByRecipeId(int recipeId) {
//        String sql = "SELECT ingredient.ingredient_id, ingredient.ingredient_name FROM ingredient " +
//                "JOIN recipe_ingredient ON ingredient.ingredient_id = recipe_ingredient.ingredient_id " +
//                "WHERE recipe_ingredient.recipe_id = ?;";
//        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, recipeId);
//        List<Ingredient> ingredients = new ArrayList<>();
//        while (rowSet.next()) {
//            ingredients.add(mapRowToIngredient(rowSet));
//        }
//        return ingredients;
//    }
//
//    private Ingredient mapRowToIngredient(SqlRowSet rowSet) {
//        Ingredient ingredient = new Ingredient(rowSet.getInt("ingredient_id"),
//                rowSet.getString("ingredient_name"));
//        return ingredient;
//    }
//
//
//
//}
//
