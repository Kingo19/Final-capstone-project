<template>
  <div class="recipe-container">
    <div class="recipe-card">
      <h1>Create Recipe</h1>
      <form @submit.prevent="createRecipe" class="formClass">
        <div class="form-group">
          <label for="recipeName">Recipe Name:</label>
          <input
              id="recipeName"
              v-model="newRecipe.name"
              type="text"
              placeholder="Enter recipe name"
              class="input-field"
          />
        </div>
        <div class="form-group">
          <label for="recipeInstructions">Instructions:</label>
          <textarea
              id="recipeInstructions"
              v-model="newRecipe.instructions"
              placeholder="Enter recipe instructions"
              class="input-field"
          ></textarea>
        </div>
        <div class="form-group">
          <h2>Available Ingredients:</h2>
          <ul class="top-level-remove-div">
            <li v-for="(ingredient, index) in availableIngredients" :key="index"
                @click="addIngredientToRecipe(ingredient)">
              {{ ingredient }}
            </li>
          </ul>
        </div>
        <div class="user-input">
          <input v-model="newIngredient" placeholder="Enter new ingredient" class="input-field"/>
          <button @click="addNewIngredient" :disabled="newIngredient.trim() === ''" class="submit-button">Add</button>
        </div>
        <div class="form-group">
          <button type="submit" class="submit-button">Create Recipe</button>
        </div>
      </form>
      <div class="form-group">
        <h2>Selected Ingredients:</h2>
        <div v-if="newRecipe.ingredients.length > 0" class="selected-ingredients">
          <div v-for="(ingredient, index) in newRecipe.ingredients" :key="index" class="selected-ingredient">
            {{ ingredient }}
          </div>
        </div>
      </div>
      <router-link to="/" class="back-link">
        <span class="back-icon">&lt; Back to Home</span>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      newRecipe: {
        name: "",
        instructions: "",
        ingredients: [],
      },
      newIngredient: "",
      availableIngredients: ["Ingredient 1", "Ingredient 2", "Ingredient 3"], // Sample available ingredients
    };
  },
  methods: {
    createRecipe() {
      // Handle logic to create a new recipe
      console.log("Created Recipe:", this.newRecipe);
      // Reset the form fields after creating the recipe
      this.newRecipe = {
        name: "",
        instructions: "",
        ingredients: [],
      };
    },
    addIngredientToRecipe(ingredient) {
      this.newRecipe.ingredients.push(ingredient);
    },
    addNewIngredient() {
      if (this.newIngredient.trim() !== "") {
        this.availableIngredients.push(this.newIngredient);
        this.newRecipe.ingredients.push(this.newIngredient);
        this.newIngredient = "";
      }
    },
  },
};
</script>

<style scoped>
.recipe-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.recipe-card {
  max-width: 600px;
  width: 100%;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
  background-color: #fff;
}

.formClass {
  display: flex;
  flex-direction: column;
}

.form-group {
  margin-bottom: 20px;
}

label {
  font-weight: bold;
  margin-bottom: 8px;
}

.input-field,
textarea {
  padding: 12px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 4px;
  transition: border-color 0.3s, box-shadow 0.3s;
  width: 100%;
  box-sizing: border-box;
}

.input-field:focus,
textarea:focus {
  border-color: #007BFF;
  box-shadow: 0 0 10px rgba(0, 123, 255, 0.2);
}

.submit-button {
  padding: 12px;
  background-color: #007BFF;
  color: #fff;
  border: none;
  cursor: pointer;
  font-size: 18px;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.submit-button:hover {
  background-color: #0056b3;
}

.top-level-remove-div {
  list-style-type: none;
  padding: 0;
}

.top-level-remove-div li {
  cursor: pointer;
  margin-bottom: 8px;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.top-level-remove-div li:hover {
  background-color: #f5f5f5;
}

.selected-ingredients {
  margin-top: 10px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.selected-ingredient {
  padding: 8px;
  margin-bottom: 8px;
  background-color: #f5f5f5;
}

.user-input {
  display: flex;
  align-items: center;
}

.user-input .input-field {
  flex-grow: 1;
  margin-right: 10px;
}
</style>