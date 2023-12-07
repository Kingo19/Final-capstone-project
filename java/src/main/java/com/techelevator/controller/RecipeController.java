package com.techelevator.controller;

import com.techelevator.dao.RecipeDao;
import com.techelevator.dao.RecipeIngredientDao;
import com.techelevator.model.RecipeDto;
import com.techelevator.model.RecipeIngredientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


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
    public void addRecipe(@RequestBody RecipeIngredientDto recipeIngredientDto) {
        try {
            recipeIngredientDao.addRecipeIngredientConnection(recipeIngredientDto);
        } catch (Exception e) {
            logger.error("Recipe Failure: ", e);
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Parameters: " + recipeIngredientDto.toString()
            );
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "recipes/modify", method = RequestMethod.POST)
    public void modifyRecipe(@RequestBody RecipeDto recipeDto) {
        try {
            System.out.println(recipeDto);
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

    @RequestMapping(path = "recipes/{id}", method = RequestMethod.GET)
    public RecipeIngredientDto fetchRecipeInfo(@Valid @PathVariable int id){
        RecipeIngredientDto recipeIngredientDto = new RecipeIngredientDto();
        try {
            recipeIngredientDto = recipeIngredientDao.getRecipeAndIngredientsFromId(id);
        } catch (Exception e) {
            logger.error("Recipe Failure: ", e);
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Parameters: " + id
            );
        }
        return recipeIngredientDto;
    }
    @RequestMapping(path = "recipes/all", method = RequestMethod.GET)
    public List<RecipeIngredientDto> fetchAllRecipeInfo(){
        System.out.println("Triggered");
        List<RecipeIngredientDto> recipeIngredientList = new ArrayList<>();
        try {
             recipeIngredientList = recipeIngredientDao.getAllRecipes();
        } catch (Exception e) {
            logger.error("Recipe Failure: ", e);
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Parameters: "
            );
        }
        int counter = 0;
        System.out.println(recipeIngredientList.get(0));
        System.out.println(counter);
        counter++;
        return recipeIngredientList;
    }
}
