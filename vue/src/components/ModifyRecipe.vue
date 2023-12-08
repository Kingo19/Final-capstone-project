<template>
  <div id="app" class="app-container">
    <form id="form" @submit.prevent="submitForm" class="recipe-form">
      <!-- Recipe Name -->
      <div class="input-group">
        <label for="recipeName">Recipe Name</label>
        <input type="text" id="recipeName" v-model="modify.recipe.recipe_name"
               :aria-required="requiredFields.recipeName.toString()"
               maxlength="200"
               :placeholder="modify.recipe.recipe_name">
      </div>

      <!-- Description -->
      <div class="input-group textfield">
        <label for="description">Description</label>
        <textarea :maxlength="maxlen" id="description" v-model="modify.recipe.recipe_instructions"
                  :aria-required="requiredFields.instructions.toString()"
                  rows="4" cols="50"
                  :placeholder="modify.recipe.recipe_instructions"></textarea>
      </div>

      <!-- Ingredients List -->
      <div class="input-group">
        <h3>Ingredients</h3>
        <div class="ingredient-group">
          <input id="ingredientName" type="text" v-model="item.ingredient_name"
                 list="ingredientNames" :placeholder="item.ingredient_name">
          <datalist id="ingredientNames">
            <option class="innerIngredient" v-for="itemName in foodName" :value="itemName"></option>
          </datalist>
          <button type="button" @click="addIngredient">Add Ingredient</button>
        </div>
      </div>

      <!-- Ingredients Items -->
      <div class="ingredient-list">
        <div class="ingredient-item" v-for="(ingredient, index) in modify.ingredients" :key="index">
          <p>{{ ingredient.ingredient_name }}</p>
          <button type="button" @click="removeIngredient(index)">Remove</button>
        </div>
      </div>

      <!-- Submit Button -->
<!--      <div class="button-group">-->
<!--        <button type="submit" class="submit-button">Modify</button>-->
<!--      </div>-->
    </form>
  </div>
</template>

<script>
import foodarray from "@/resources/foodNameArray";
import RecipeService from "../services/RecipeService";

export default {
  props:["modify"],
  data() {
    return {
      originalName: "",
      formValid: false,
      recipe_name: "Name your recipe, we suggest something unique and easy to remember",
      Instructions: "How to prepare your amazing recipe. ",
      maxlen: 200,
      item: { ingredient_name: '' },
      requiredFields: {
        recipeName: true,
        instructions: true,
        ingredients: true,
      },
      foodName: foodarray.fullFoodNameArray,
      formData: {
        recipe: {
          recipe_name: "",
          recipe_instructions: "",
        },
        ingredients: []
      },
    }
  },
  methods: {

    submitForm() {
      console.log(this.formData)
      RecipeService.addRecipeAndIngredients(this.formData);
    },
    addIngredient() {
      if (this.item.ingredient_name) {
        this.formData.ingredients.push({ ...this.item });
        this.item = { ingredient_name: '' };
      } else {
        alert("Ingredient name cannot be empty.");
      }
    },
    removeIngredient(index) {
      this.formData.ingredients.splice(index, 1);
    },
  },
  computed: {
    checkIfFormIsValid() {
      return this.formData.recipe.recipe_name &&
          this.formData.recipe.recipe_instructions &&
          this.formData.ingredients.length > 0;
    },

  }
}
</script>

<style scoped>


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
