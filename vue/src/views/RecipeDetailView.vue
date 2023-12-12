<template>
  <div>
    <RecipeDetailPage v-for="recipeDto in this.allRecipe" :key="recipeDto" :recipe-dto="recipeDto"/>
  </div>
</template>

<script>
import RecipeDetailPage from "../components/RecipeDetailPage.vue";
import RecipeService from "../services/RecipeService";

export default {
  components: {
    RecipeDetailPage,
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
