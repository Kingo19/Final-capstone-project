package com.techelevator.unused;

public class UnusedController {
    //All code works.
// Original Reference Code
    //////////////////////////////
    // Adds a single ingredient to the datebase.
    // Successfully implemented and tested
//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(path = "ingredients/add", method = RequestMethod.POST)
//    public void addIngredient(@RequestBody IngredientDto ingredientDto) {
//        try {
//            ingredientDao.addIngredient(ingredientDto);
//        } catch (Exception e){
//            System.out.println("Didn't work. :/" + e);
//        }
//    }
//
//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(path = "ingredients/add-multiple", method = RequestMethod.POST)
//    public void addIngredients(@RequestBody List<IngredientDto> ingredientDtoList) {
//        try {
//            List<Integer> idsList = recipeIngredientDao.getIngredientIds(ingredientDtoList);
//            System.out.println(idsList.toString());
//        } catch (Exception e){
//            System.out.println("Didn't work. :/" + e);
//        }
//    }
    //////////////////////////////

//
//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(path = "recipes/addwithingredients", method = RequestMethod.POST)
//    public void addRecipeAndIngredients(@RequestBody RecipeIngredientDto recipeIngredientDto){
//        try {
//            recipeIngredientDao.addRecipeAndIngredients(recipeIngredientDto);
//        } catch (Exception e){
//            System.out.println("Recipe Ingredient Failure. " + e);
//        }
//    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(path = "recipes/add", method = RequestMethod.POST)
//    public void addRecipe(@RequestBody RecipeDto recipeDto){
//        try {
//            recipeDao.addRecipe(recipeDto);
//        } catch (Exception e){
//            System.out.println("Recipe Failure. " + e);
//        }
//    }

    //temp for demo. move to unused
//    @RequestMapping(path = "recipes/all", method = RequestMethod.GET)
//    public List<RecipeIngredientDto> fetchAllRecipeInfo(){
//        System.out.println("Triggered");
//        List<RecipeIngredientDto> recipeIngredientList = new ArrayList<>();
//        try {
//             recipeIngredientList = recipeIngredientDao.getAllRecipes();
//        } catch (Exception e) {
//            logger.error("Recipe Failure: ", e);
//            System.out.printf("%s%n%s%n%s%n%s%n",
//                    "Class: " + this.getClass(),
//                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
//                    "Exception: " + e,
//                    "Parameters: "
//            );
//        }
//        int counter = 0;
//        System.out.println(recipeIngredientList.get(0));
//        System.out.println(counter);
//        counter++;
//        return recipeIngredientList;
//    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(path = "user/recipes/save", method = RequestMethod.POST)
//    public void saveRecipe(Principal principal, @Valid @RequestBody RecipeDto recipeToSave){
//        int userId = userDao.getUserByUsername(principal.getName()).getId();
//        int recipeId = recipeDao.getRecipeID(recipeToSave);
//        recipeIngredientDao.addRecipetoUser(userId, recipeId);
//    }

//    @RequestMapping(path = "user", method = RequestMethod.GET)
//    public List<String> getUserInfo(Principal principal){
//        List<String> stringList = new ArrayList<>();
//        stringList.add(principal.getName());
//        int userId = userDao.getUserByUsername(principal.getName()).getId();
//        stringList.add(userId +"");
//        return stringList;
//    }

}
