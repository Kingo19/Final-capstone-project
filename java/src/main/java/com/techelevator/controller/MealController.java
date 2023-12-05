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
}
