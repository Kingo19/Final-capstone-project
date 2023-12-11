package com.techelevator.controller;

import com.techelevator.dao.MealDao;
import com.techelevator.dao.RecipeDao;
import com.techelevator.dao.RecipeIngredientDao;
import com.techelevator.dao.TypeDao;
import com.techelevator.model.MealDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/meals")
public class MealController {
    private final RecipeDao recipeDao;
    private final RecipeIngredientDao recipeIngredientDao;
    private final MealDao mealDao;
    private final TypeDao typeDao;

    // Single constructor with @Autowired (if using Spring)
    public MealController(RecipeDao recipeDao, RecipeIngredientDao recipeIngredientDao, MealDao mealDao, TypeDao typeDao) {
        this.recipeDao = recipeDao;
        this.recipeIngredientDao = recipeIngredientDao;
        this.mealDao = mealDao;
        this.typeDao = typeDao;
    }

    @PostMapping
    public ResponseEntity<?> createMeal(@RequestBody MealDto meal, @RequestParam int typeId) {
        int mealId = mealDao.createMeal(meal);
        typeDao.associateMealWithType(mealId, typeId);
        return ResponseEntity.ok().build();
    }
}
