package com.techelevator.controller;


import com.techelevator.dao.IngredientDao;
import com.techelevator.dao.RecipeDao;
import com.techelevator.dao.RecipeIngredientDao;
import com.techelevator.model.IngredientDto;
import com.techelevator.model.RecipeDto;
import com.techelevator.model.RecipeIngredientDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class MealController {
//    private final IngredientDao ingredientDao;
    private final RecipeDao recipeDao;
    private final RecipeIngredientDao recipeIngredientDao;

    public MealController(RecipeIngredientDao recipeIngredientDao, RecipeDao recipeDao) {
//        this.ingredientDao = ingredientDao; IngredientDao ingredientDao
        this.recipeIngredientDao = recipeIngredientDao;
        this.recipeDao = recipeDao;
    }

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
}
