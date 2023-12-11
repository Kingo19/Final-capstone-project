package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.Meal;
import com.techelevator.model.MealDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@CrossOrigin
public class MealController {
    private final RecipeDao recipeDao;
    private final RecipeIngredientDao recipeIngredientDao;
    private final MealDao mealDao;
    private final UserDao userDao;

    // Single constructor with @Autowired (if using Spring)
    public MealController(RecipeDao recipeDao, RecipeIngredientDao recipeIngredientDao, MealDao mealDao, UserDao userDao) {
        this.recipeDao = recipeDao;
        this.recipeIngredientDao = recipeIngredientDao;
        this.mealDao = mealDao;
        this.userDao = userDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "user/meals/add", method = RequestMethod.POST)
    public Meal createMeal(Principal principal, @Valid @RequestBody MealDto mealDto) {
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        return mealDao.createMeal(mealDto,userId);
    }

    @RequestMapping(path = "user/meals/{id}", method =  RequestMethod.GET)
    public Meal getMealById(Principal principal, @PathVariable int id){
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        return mealDao.getMealByMealID(id, userId);
    }
}
