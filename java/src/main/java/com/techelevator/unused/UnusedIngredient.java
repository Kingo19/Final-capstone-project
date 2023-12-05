package com.techelevator.unused;

public class UnusedIngredient {
    // Original Reference Code
    //All code works.
//    public int addIngredient(IngredientDto ingredientDto){
//        int resultID = ingredientDto.getId();
//        try{
//            ingredientDto.setId(getIngredientID(ingredientDto));
//        } catch (Exception e) {
//            logger.error("Error adding ingredient: ", e);
//            System.out.printf("%s%n%s%n%s%n%s%n",
//                    "Class: " + this.getClass(),
//                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
//                    "Exception: " + e,
//                    "Parameters: " + ingredientDto.toString()
//            );
//        }
//        if(resultID == 0){
//            String sql = "INSERT INTO ingredient(ingredient_name) VALUES (?) RETURNING ingredient_id;";
//            jdbcTemplate.queryForObject(sql, int.class,ingredientDto.getIngredient_name());
//            ingredientDto.setId( jdbcTemplate.queryForObject(sql, int.class,ingredientDto.getIngredient_name()));
//        }
//        return ingredientDto.getId();
//    }
//
//    public int getIngredientID(IngredientDto ingredientDto){
//        int id = 0;
//        String sql = "SELECT ingredient_id FROM ingredient WHERE ingredient_name = ?;";
//        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, ingredientDto.getIngredient_name());
//        while(result.next()){
//            id = result.getInt("ingredient_id");
//        }
//        return id;
//    }
}
