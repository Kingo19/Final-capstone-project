<template>
  <div>
    <!--      This is where you will loop over the response of data from the server to get each or every recipe-->
    <RecipeDetailPage  v-for="recipeDto in getAll" :key="recipeDto" :recipeDto="recipeDto"/>
  </div>
</template>

<script>
import RecipeDetailPage from "@/components/RecipeDetailPage.vue";
import RecipeService from "@/services/RecipeService";

export default {
  components: {
    RecipeDetailPage,
  },
  data(){
    return {
      allRecipe: [],
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

  },
  created() {
    console.log("Trigger")
    this.allRecipe = RecipeService.getAllRecipes();
    console.log(this.allRecipe[0])
  },
  computed:{
    getAll(){
      console.log(this.allRecipe[0])
      return this.allRecipe

    }
  }
};
</script>
