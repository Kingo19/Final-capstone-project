package com.techelevator.unused;

public class UnusedRecipeIngredient {
    //Original Code for Reference
    // All other code works
//    @Override
//    public void addRecipeAndIngredients(RecipeIngredientDto recipeIngredientDto) {
//        String sql = "INSERT INTO recipe_ingredient(recipe_id, ingredient_id) VALUES (?,?);";
//
//        int recipeResult = jdbcRecipeDao.addRecipe(recipeIngredientDto.getRecipe());
//        System.out.println(recipeResult);
//        System.out.println("-------RECIPE ID-------");
//        List<Integer> ingredientIds = getIngredientIds(recipeIngredientDto.getIngredients());
//        for(Integer currentId : ingredientIds){
//            jdbcTemplate.update(sql, recipeResult, currentId);
//            System.out.println(currentId + "ingredient id");
//            System.out.println(recipeResult + "Recipe id");
//        }
//    }

    //BUILD ERRORS
//@Override
//public void addRecipeIngredients(List<IngredientDto> ingredients, int recipeId) {
//    int ingredientsToAdd = ingredients.size();
//    int ingredientsAdded = 0;
//    String sql = "INSERT INTO recipe_ingredient(recipe_id, ingredient_id) VALUES (?, ?)";
//    try {
//        for (IngredientDto ingredient : ingredients) {
//            jdbcTemplate.update(sql, recipeId, ingredient.getId());
//            ingredientsAdded++;
//            System.out.println("Ingredient added to the recipe. Recipe ID: " + recipeId + ", Ingredient ID: " + ingredient.getId());
//        }
//        if (ingredientsToAdd != ingredientsAdded) {
//            throw new RuntimeException("Didn't create the expected number of rows.");
//        } else {
//            System.out.println("All ingredients added successfully.");
//        }
//    } catch (CannotGetJdbcConnectionException e) {
//        System.out.println("Unable to connect to the database.");
//        throw new RuntimeException("Unable to connect to the database.", e);
//    } catch (DataIntegrityViolationException e) {
//        System.out.println("Action would violate data integrity.");
//        throw new RuntimeException("Action would violate data integrity.", e);
//    } catch (BadSqlGrammarException e) {
//        System.out.println("Invalid syntax.");
//        throw new RuntimeException("Invalid syntax.", e);
//    }
//}
}
