<template>
  <div id="mealApp" class="meal-container">
    <form id="mealForm" @submit.prevent="submitMeal" class="meal-form">
      <!-- Meal Name -->
      <div class="input-group">
        <label for="mealName">Meal Name</label>
        <input type="text" id="mealName" v-model="mealData.mealName"
               required
               :maxlength="maxLenInput"
               placeholder="Enter meal name">
      </div>

      <!-- Meal Type -->
      <div class="input-group">
        <label for="mealType">Meal Type</label>
        <select id="mealType" v-model="mealData.type">
          <option v-for="(eachType, num) in types" :key="num"  :value="eachType">{{ eachType }}</option>
        </select>
      </div>

      <div class="input-group">
        <h3>Add a recipe to your meal</h3>
        <div class="name-group">
          <label for="recipeNames"></label>
          <select :disabled="recipeNamesToCheck.length === 0" id="recipeName" v-model="item.recipeName">
            <option class="recipe-item" v-for="(recipe, index) in recipeNamesToCheck" :key="index">{{recipe}}</option>
          </select>
          <button type="button" @click="addRecipe">Add Ingredient</button>
        </div>
      </div>

      <!-- recipes Items -->
      <div class="recipe-list">
        <div class="recipe-item" v-for="(recipe, index) in mealData.recipeNames" :key="index">
          <p>{{ recipe }}</p>
          <button type="button" @click="removeRecipe(index)">Remove</button>
        </div>
      </div>

      <!-- Submit Button -->
      <div class="button-group">
        <button type="submit" class="submit-button">Submit Meal Plan</button>
      </div>
    </form>
  </div>
</template>

<script>
import RecipeService from "@/services/RecipeService";

export default {
  data() {
    return {
      recipeNamesToCheck: [],
      item: { recipeName: '' },
      maxLenInput: 255,
      mealData: {
        mealName: "",
        recipeNames: [],
        type: ""
      },
      types:["Breakfast","Brunch","Lunch","Dinner","Appetizer","Salad","Main-course","Side-dish","Baked-goods","Dessert",
        "Snack","Soup","Holiday", "Vegetarian"]

    }
  },
  methods: {

    submitMeal() {
      console.log(this.mealData)
      let response = RecipeService.addMeal(this.mealData).then(cu => {
        let save = cu.data
      })

      this.mealData = {
        mealName: "",  recipes: [],  type: ""
      }
    },

    addRecipe(index) {
      let removeFromArray = this.recipeNamesToCheck
      let removedElement = removeFromArray[index]
      removeFromArray.splice(index, 1);

      if (this.item.recipeName) {
        this.mealData.recipeNames.push(this.item.recipeName);
        this.item = {recipeName: ''};
      }
    },
    removeRecipe(index) {
      let removeFromArray = this.mealData.recipeNames
      let removedElement = removeFromArray[index]
      removeFromArray.splice(index, 1);
      this.recipeNamesToCheck.push(removedElement.recipeName);
    },


    namesArrayCheck() {
      RecipeService.getUserRecipeNames().then(cu => {
        this.recipeNamesToCheck = cu.data;
        console.log(this.recipeNamesToCheck)
      })
    },

  },
  mounted() {
    this.namesArrayCheck()
  }
}
</script>

<style scoped>

.meal-container {
  font-family: Arial, sans-serif;
  background-color: rgb(239, 234, 231);
  min-height: 100vh;
  padding: 20px;
}

.meal-form{
  background-color: #fff8dc;
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.input-group {
  margin-bottom: 20px;
  width: 100%;
}

label {
  display: block;
  font-size: 1.2em;
  font-weight: bold;
  margin-bottom: 10px;
  color: #333;
}

input[type='text'], textarea, select {
  width: 100%;
  padding: 5px;
  border: 2px solid #ccc;
  border-radius: 5px;
  font-size: 1em;
  color: black;
}

.name-group {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.recipe-list {
  display: flex;
  flex-direction: column;
}

.recipe-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.button-group {
  display: flex;
  justify-content: center;
  width: 100%;
}

.submit-button {
  background-color: #ffcc00;
  border: none;
  color: #333;
  padding: 10px 15px;
  text-transform: uppercase;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.submit-button:hover {
  background-color: #e6b800;
}

@media (max-width: 768px) {
  .meal-plan-form {
    max-width: 90%;
  }
}

</style>
