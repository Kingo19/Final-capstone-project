package com.techelevator.controller;

import com.techelevator.dao.IngredientDao;
import com.techelevator.dao.RecipeIngredientDao;
import com.techelevator.model.Ingredient;
import com.techelevator.model.IngredientDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class IngredientsController {
    private static final Logger logger = LoggerFactory.getLogger(MealController.class);
    private final IngredientDao ingredientDao;
    private final RecipeIngredientDao recipeIngredientDao;

    public IngredientsController(IngredientDao ingredientDao, RecipeIngredientDao recipeIngredientDao) {
        this.ingredientDao = ingredientDao;
        this.recipeIngredientDao = recipeIngredientDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "check", method = RequestMethod.GET)
    public void tester(){
        System.out.println("Good to go");
    }

    // Adds a single ingredient to the datebase.
    // Successfully implemented and tested
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "ingredients/add", method = RequestMethod.POST)
    public void addIngredient(@RequestBody IngredientDto ingredientDto) {
        try {
            int id = ingredientDao.addIngredientReturnId(ingredientDto);
            System.out.println(id);
        } catch (Exception e){
            logger.error("Error adding ingredient: ", e);
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Parameters: " + ingredientDto.toString()
            );
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "ingredients/add-multiple", method = RequestMethod.POST)
    public void addIngredientsGetIdList(@RequestBody List<IngredientDto> ingredientDtoList) {
        //Gets back list of id's
        try {
            List<Integer> idsList = recipeIngredientDao.getIngredientIds(ingredientDtoList);
            System.out.println(idsList.toString());
        } catch (Exception e){
            logger.error("Error adding ingredient: ", e);
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Parameters: " + ingredientDtoList.toString()
            );
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "ingredients/addtest", method = RequestMethod.POST)
    public void addIngredientGetList(@RequestBody List<IngredientDto> ingredientDtoList) {
        List<Ingredient> ing = new ArrayList<>();
        try {
            for(IngredientDto ingd : ingredientDtoList){
                ing.add(ingredientDao.getIngredient(ingd));
            }
            System.out.println(ing.toString());
        } catch (Exception e){
            logger.error("Error adding ingredient: ", e);
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Parameters: " + ingredientDtoList.toString()
            );
        }
    }
}
