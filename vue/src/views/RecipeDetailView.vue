<template>
  <div>
<!--    <RecipeDetailPage v-for="recipeDto in this.allRecipe" :key="recipeDto" :recipe-dto="recipeDto"/>-->
    <test-details-page v-for="recipeDto in this.allRecipe" :key="recipeDto" :recipe-dto="recipeDto"></test-details-page>
  </div>
</template>

<script>
/*import RecipeDetailPage from "../components/RecipeDetailPage.vue";*/
import RecipeService from "../services/RecipeService";
import TestDetailsPage from "@/components/testDetailsPage.vue";

export default {
  components: {
    TestDetailsPage,
/*    RecipeDetailPage,*/
  },
  data() {
    return {
      singleRecipe:false,
      allRecipe: null,
      singleRecipeObject:null,
    }
  },

  methods: {
    getting() {
         RecipeService.getAllRecipes(this.user).then(response => {
           this.allRecipe = response.data
         })
    },

  },
  mounted() {
    this.getting()
  },
};
</script>

<style scoped>

</style>
