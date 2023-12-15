<template>
    <div></div>
</template>
<!--      <div class="card-image">
        <img src="https://s-media-cache-ak0.pinimg.com/736x/57/98/9f/57989f2a2e186e38bf93429aa395120c.jpg" alt="Recipe Image" />
      </div>-->


<!--      <div>
        <button class="collapsible" @click="toggleCollapse">{{ title }}</button>
        <div class="content" v-show="isCollapsed">
          <slot></slot>
        </div>
      </div>-->












<!--          <div class="ingredients" v-show="activeTab === 'ingredients'">
            <h3>Ingredients</h3>
            <ul>
              <li v-for="ingredient in recipeDto.ingredients"  :key="ingredient"> {{ ingredient.ingredient_name }}</li>
            </ul>
          </div>

          <div class="instructions" v-show="activeTab === 'instructions'">
            <h3>Instructions</h3>
            <p>{{ recipeDto.recipe.recipe_instructions}}</p>
          </div>-->
<script>
import RecipeService from '../services/RecipeService';

export default {
    data() {
        return {
            meal_plan: []
        }
    },
    methods: {
        getAllUserPlans() {
            RecipeService.getNextWeeksPlan().then(response => {
                this.meal_plan = response.data
                console.log(this.userDailyPlans)
            })
        },
    },
    mounted(){
        this.getAllUserPlans()
    }
}

</script>

<style></style>