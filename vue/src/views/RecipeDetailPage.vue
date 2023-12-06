<template>
    <div>
      <RecipeDetailView  v-for="recipeDto in testrecipe" :key="recipeDto" :recipe-dto="recipeDto"/>
    </div>
  </template>
  
  <script>
  import RecipeDetailView from "@/components/RecipeDetailView.vue";
  
  export default {
    components: {
      RecipeDetailView,
    },
    data(){
      return {
        testrecipe: [
          {
            "recipe_name": "Chocolate Cake",
            "recipe_instructions": "Instructions for Chocolate Cake.",
            "ingredients": [
              {"ingredient_name": "Flour"},
              {"ingredient_name": "Sugar"},
              {"ingredient_name": "Cocoa Powder"}
            ]
          },
          {
            "recipe_name": "Pasta Carbonara",
            "recipe_instructions": "Instructions for Pasta Carbonara.",
            "ingredients": [
              {"ingredient_name": "Pasta"},
              {"ingredient_name": "Eggs"},
              {"ingredient_name": "Bacon"}
            ]
          },
          {
            "recipe_name": "Caesar Salad",
            "recipe_instructions": "Instructions for Caesar Salad.",
            "ingredients": [
              {"ingredient_name": "Lettuce"},
              {"ingredient_name": "Croutons"},
              {"ingredient_name": "Parmesan Cheese"}
            ]
          },
          {
            "recipe_name": "French Toast",
            "recipe_instructions": "Instructions for French Toast.",
            "ingredients": [
              {"ingredient_name": "Bread"},
              {"ingredient_name": "Eggs"},
              {"ingredient_name": "Milk"}
            ]
          },
          {
            "recipe_name": "Vegetable Stir Fry",
            "recipe_instructions": "Instructions for Vegetable Stir Fry.",
            "ingredients": [
              {"ingredient_name": "Bell Peppers"},
              {"ingredient_name": "Broccoli"},
              {"ingredient_name": "Carrots"}
            ]
          }
        ]
      }

    }
  };
  </script>
  