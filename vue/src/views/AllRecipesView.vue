<template>
  <div class="card innerBody">
  <small-recipe-cards v-for="recipeDto in this.allRecipe" :key="recipeDto" :recipe-dto="recipeDto" ></small-recipe-cards>
  </div>

</template>

<script>
import SmallRecipeCards from "@/components/AllRecipeSmallRecipeCards.vue";
import recipeService from "@/services/RecipeService";
import testSelectedRecipe from "@/components/testSelectedRecipe.vue";

export default {
  components: {
    SmallRecipeCards,
  },
  data(){
    return {
      allRecipe:this.$store.state.recipes,
    }

  },
  methods:{
    setArrayRecipes(){
      let get = recipeService.getAllRecipes(this.user).then(cu =>
      {this.allRecipe = cu.data
        this.$store.commit("SETRECIPES", cu.data)
      });
    },
  },
  computed:{
    switchState(){
      return this.$store.state.switchView
    }
  },

  created() {
    this.setArrayRecipes()
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

