<template>
  <div>
    <!--      This is where you will loop over the response of data from the server to get each or every recipe-->
    <RecipeDetailPage v-if="!singleRecipe" v-for="recipeDto in this.allRecipe" :key="recipeDto" :recipe-dto="recipeDto"/>
    <RecipeDetailPageSingle v-if="singleRecipe" :recipe-dto="singleRecipeObject"/>
  </div>
</template>

<script>
import RecipeDetailPage from "../components/RecipeDetailPage.vue";
import RecipeService from "../services/RecipeService";
import RecipeDetailPageSingle from "@/components/RecipeDetailPageSingle.vue";

export default {
  components: {
    RecipeDetailPageSingle,
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
           this.singleRecipe = response.data.length === 1
           if(this.singleRecipe){
             this.singleRecipeObject = response.data[0]
           }else{
           this.allRecipe = response.data
           }
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
