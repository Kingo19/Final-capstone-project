<template>
  <div class="card">
  <small-recipe-cards v-for="recipeDto in this.allRecipe" :key="recipeDto" :recipe-dto="recipeDto" ></small-recipe-cards>
  </div>



<!--  Could not get this function to work-->

<!--  <div class="card" v-if="!switchState">
    <small-recipe-cards v-for="recipeDto in this.allRecipe" :key="recipeDto" :recipe-dto="recipeDto" ></small-recipe-cards>
  </div>
  <div class="card" v-if="switchState">
    <test-selected-recipe></test-selected-recipe>
  </div>-->

</template>

<script>
import SmallRecipeCards from "@/components/AllRecipeSmallRecipeCards.vue";
import recipeService from "@/services/RecipeService";
import testSelectedRecipe from "@/components/testSelectedRecipe.vue";

export default {
  components: {
    SmallRecipeCards,
    testSelectedRecipe,
  },
  data(){
    return {
      allRecipe:[],
    }

  },
  methods:{
    setArrayRecipes(){
      let get = recipeService.getAllRecipes(this.user).then(cu =>
      {this.allRecipe = cu.data
        this.$store.commit("setRecipes", cu.data)
      });
      console.log(this.allRecipe)
    },
  },
  computed:{
    switchState(){
      console.log(this.$store.state.switchView)
      return this.$store.state.getters.getSwitch()
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

