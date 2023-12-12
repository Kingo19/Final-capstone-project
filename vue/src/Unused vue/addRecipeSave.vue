<template>
  <div id="app" class="app-container">
    <form id="form" v-on:submit.prevent="submitForm" class="recipe-form">
      <!-- Recipe Name -->
      <div class="input-group">
        <h3>Recipe Name</h3>
        <div class="recipe-group">
          <input type="text" id="recipeName" v-model="formData.recipe.recipe_name"
                 list="recipeNames"
                 :aria-required="requiredFields.recipeName.toString()"
                 required
                 :max="maxLenInput"
                 placeholder="Name your recipe, please enter a unique recipe name">
          <datalist id="recipeNames">
            <option class="innerRecipeNames" v-for="itemRecipeName in this.recipeNamesToCheck"
                    :key="itemRecipeName"
                    :value="itemRecipeName" ></option>
          </datalist>
          <h2 id="problem1" v-if="isNameInDatabase">{{ badNamePrompt }}</h2>
        </div>
      </div>

      <!-- Description -->
      <div class="input-group textfield">
        <label for="description">Instructions</label>
        <textarea :maxlength="maxLenTextArea"
                  id="description" v-model="formData.recipe.recipe_instructions"
                  :aria-required="requiredFields.instructions.toString()"
                  required
                  rows="4" cols="50"
                  placeholder="A brief description of the dish."></textarea>
      </div>

      <!-- Ingredients List -->
      <div class="input-group">
        <h3>Ingredients</h3>
        <div class="ingredient-group">
          <input id="ingredientName" type="text" v-model="item.ingredient_name"
                 list="ingredientNames"
                 placeholder="Ingredient Name">
          <datalist id="ingredientNames">
            <option class="innerIngredient" v-for="itemName in foodName" :key="itemName" :value="itemName"></option>
          </datalist>
          <button type="button" @click="addIngredient">Add Ingredient</button>
        </div>
      </div>

      <!-- Ingredients Items -->
      <div class="ingredient-list">
        <div class="ingredient-item" v-for="(ingredient, index) in formData.ingredients" :key="index">
          <p>{{ ingredient.ingredient_name }}</p>
          <button type="button" @click="removeIngredient(index)">Remove</button>
        </div>
      </div>

      <!-- Submit Button -->
      <div class="button-group">
        <button type="submit" class="submit-button">Submit</button>
      </div>
    </form>
    <button @click="getArrayOfNames"></button>
  </div>
</template>

<script>
import foodarray from "@/resources/foodNameArray";
import RecipeService from "../services/RecipeService";

export default {
  data() {
    return {
      badNamePrompt:"You have entered a name already in our database. Please try again",
      recipeNamesToCheck: [],
      formValid: false,
      recipe_name: "Name your recipe, we suggest something unique and easy to remember",
      Instructions: "How to prepare your amazing recipe. ",
      maxLenInput: 255,
      maxLenTextArea: 1999,
      item: { ingredient_name: '' },
      requiredFields: {
        recipeName: true,
        instructions: true,
        ingredients: true,
      },
      foodName: foodarray.fullFoodNameArray,
      formData: {
        recipe: {
          recipe_name: "test",
          recipe_instructions: "",
        },
        ingredients: []
      },
    }
  },
  methods: {

    submitForm() {
      RecipeService.addRecipeAndIngredients(this.formData);
    },
    addIngredient() {
      if (this.item.ingredient_name) {
        this.formData.ingredients.push({...this.item});
        this.item = {ingredient_name: ''};
      } else {
        alert("Ingredient name cannot be empty.");
      }
    },
    removeIngredient(index) {
      this.formData.ingredients.splice(index, 1);
    },
    namesArrayCheck() {
      RecipeService.getUserRecipeNames().then(cu => {
        this.recipeNamesToCheck = cu.data;
        console.log(this.recipeNamesToCheck)
      })
    },

    getArrayOfNames(){
      console.log("getArrayOfNames")
      console.log(this.recipeNamesToCheck)
    },

    checkRequiredNames(){
      return !this.isNameInDatabase() &&
          this.formData.recipe.recipe_name.length > 1;
    },

  },

  computed: {
    // checkIfFormIsValid() {
    //   return this.formData.recipe.recipe_name &&
    //       this.formData.recipe.recipe_instructions &&
    //       this.formData.ingredients.length > 0;
    // },

    isNameInDatabase(){
      return this.recipeNamesToCheck.includes(this.formData.recipe.recipe_name)
    }
  },
  mounted() {
    this.namesArrayCheck()
  }

}
</script>

<style scoped>

#problem1{
  color:red;
}


@font-face {
  font-family: "MV Boli";
  src: url("../resources/mvboli.ttf");
}

.app-container {
  font-family: "MV Boli";
  background-color: rgb(239 234 231);
  min-height: 100vh;
  padding: 20px;
}

.recipe-form {
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

input[type='text'], textarea {
  width: 100%;
  padding: 5px;
  border: 2px solid #ccc;
  border-radius: 5px;
  font-size: 1em;
  color: #333;
}

.ingredient-group {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.ingredient-list {
  display: flex;
  flex-direction: column;
}

.ingredient-item {
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
  border-radius: 5px;
  font-size: 1.2em;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
}

.submit-button:hover {
  background-color: #45A049;
}

@media (max-width: 768px) {
  .recipe-form {
    max-width: 90%;
  }
}
</style>
