<template>
  <div class="card">
    <small-recipe-cards v-for="recipeDto in this.allRecipe" :key="recipeDto" :recipe-dto="recipeDto" ></small-recipe-cards>
  </div>
</template>

<script>
/*import RecipeDetailPage from "../components/RecipeDetailPage.vue";*/
import RecipeService from "../services/RecipeService";
import TestDetailsPage from "@/components/testDetailsPage.vue";
import TestSelectedRecipe from "@/components/testSelectedRecipe.vue";
import SmallRecipeCards from "@/components/AllRecipeSmallRecipeCards.vue";
export default {
  components: {
    SmallRecipeCards,
    /*    TestDetailsPage,*/
/*    RecipeDetailPage,*/
  },
  data() {
    return {
/*      recipeDto:
          {recipe:{
              "recipe_name": "Chocolate Cake",
              "recipe_instructions": "Instructions for Chocolate Cake.",
            },
            "ingredients": [
              {"ingredient_name": "Flour"},
              {"ingredient_name": "Sugar"},
              {"ingredient_name": "Cocoa Powder"}
            ]
          },*/
      singleRecipe:false,
      allRecipe: null,
      singleRecipeObject:null,
    }
  },

  methods: {
    getting() {
         RecipeService.getAllRecipes(this.user).then(response => {
           this.allRecipe = response.data
           this.$store.commit("setRecipeObjs", this.allRecipe)
         })
    },

    a(){
      return this.allRecipe[0]
    }

  },
  mounted() {
    this.getting()
  },
};
</script>

<style scoped>

.card{
  padding: 20px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
/*  width: 100%;
  height: 100%;*/
  gap: 10px; /* Adds space between cards */
}

</style>
