package com.techelevator.unused;

public class UnusedRecipe {
    // Original Reference Code
    //All code works.
// Original Reference Code
    //All code works.
//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(path = "recipes/add", method = RequestMethod.POST)
//    public void addRecipe(@RequestBody RecipeDto recipeDto){
//        try {
//            recipeDao.addRecipe(recipeDto);
//        } catch (Exception e){
//            System.out.println("Recipe Failure. " + e);
//        }
//    }
//    @Override
//    public int addRecipe(RecipeDto recipeDto) {
//        int resultID = 0;
//        try{
//            resultID = getRecipeID(recipeDto);
//        } catch (Exception e) {
//            System.out.println("Recipe Problem. " + e);
//        }
//        if(resultID == 0){
//            String sql = "INSERT INTO recipe(recipe_name, recipe_instructions) VALUES (?,?) RETURNING recipe_id;";
//            resultID = jdbcTemplate.queryForObject(sql, int.class,recipeDto.getRecipe_name(), recipeDto.getRecipe_instructions());
//        }
//        return resultID;
//    }
//
//    public int getRecipeID(RecipeDto recipeDto){
//        int id = 0;
//        String sql = "SELECT recipe_id FROM recipe WHERE recipe_name = ?;";
//        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, recipeDto.getRecipe_name());
//        while(result.next()) {
//            id = result.getInt("recipe_id");
//        }
//        return id;
//    }
}
