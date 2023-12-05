package com.techelevator.controller;

import com.techelevator.dao.RecipeDao;
import com.techelevator.dao.RecipeIngredientDao;
import com.techelevator.model.RecipeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class RecipeController {
    private static final Logger logger = LoggerFactory.getLogger(MealController.class);
    private final RecipeDao recipeDao;
    private final RecipeIngredientDao recipeIngredientDao;

    public RecipeController(RecipeDao recipeDao, RecipeIngredientDao recipeIngredientDao) {
        this.recipeDao = recipeDao;
        this.recipeIngredientDao = recipeIngredientDao;
    }
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

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "recipes/add", method = RequestMethod.POST)
    public void addRecipe(@RequestBody RecipeDto recipeDto) {
        try {
            int recipeId = recipeDao.addRecipeReturnId(recipeDto);
            // Handle the case where the recipe ID is 0, which indicates an issue
        } catch (Exception e) {
            logger.error("Recipe Failure: ", e);
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Parameters: " + recipeDto.toString()
            );
        }
    }
}
