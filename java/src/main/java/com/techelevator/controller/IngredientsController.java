package com.techelevator.controller;

import com.techelevator.dao.IngredientDao;
import com.techelevator.dao.RecipeIngredientDao;
import com.techelevator.model.Ingredient;
import com.techelevator.model.IngredientDto;
import com.techelevator.model.RecipeDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
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

    @RequestMapping(path = "ingredients/{id}", method = RequestMethod.GET)
    public IngredientDto fetchIngredientInfo(@Valid @PathVariable int id){
        IngredientDto ingredientDto = new IngredientDto();
        try {
            ingredientDto = ingredientDao.getIngredientFromId(id);
        } catch (Exception e) {
            logger.error("Recipe Failure: ", e);
            System.out.printf("%s%n%s%n%s%n%s%n",
                    "Class: " + this.getClass(),
                    "Method: " + new Throwable().getStackTrace()[0].getMethodName(),
                    "Exception: " + e,
                    "Parameters: " + id
            );
        }
        return ingredientDto;
    }
}
