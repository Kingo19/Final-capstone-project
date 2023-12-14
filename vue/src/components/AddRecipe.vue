<template>
  <div id="app" class="app-container">
    <form id="form" v-on:submit.prevent="submitForm" class="formClass">
      <!-- Recipe Name -->
      <div class="input-group">
        <h3>Recipe Name</h3>
        <div class="name-group">
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
        <div class="add-items">
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
      <div class="top-level-remove-div">
        <div class="show-list-added-items-or-remove" v-for="(ingredient, index) in formData.ingredients" :key="index">
          <p>{{ ingredient.ingredient_name }}</p>
          <button type="button" @click="removeIngredient(index)">Remove</button>
        </div>
      </div>

      <!-- Submit Button -->
      <div class="button-group">
        <button type="submit" class="submit-button">Submit</button>
      </div>
    </form>
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

#app {
  display: flex;
  justify-content: center;
  align-content: center;
}

.app-container {
  /*  background-color: rgb(239 234 231);*/
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin: 10px;
  background-image: url("https://images.unsplash.com/photo-1498837167922-ddd27525d352?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");;
  min-height: 100vh;
}

.formClass {
/*  background-color: #fff8dc;
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);*/
  background-color: #fff8dc;
  border-radius: 10px;
  padding: 20px;
  position: relative;
  top: -100px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  width: 60%; /* Adjust the width as per your requirement */
  max-width: 800px; /* Set a max-width to ensure it doesn't get too wide on larger screens */
  margin: auto; /* This will center the form if it's smaller than the parent container */
  z-index: 1;
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

.add-items {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.top-level-remove-div {
  display: flex;
  flex-direction: column;
}

.show-list-added-items-or-remove {
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
.app-container::before {
  content: "";
  position: absolute;
  top: 80px;
  width: 100%;
  height: 130%;
  background: linear-gradient(rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.2));
}

/*.submit-button:hover {
  background-color: #45A049;
}*/

/*@media (max-width: 768px) {
  .recipe-form {
    max-width: 90%;
  }
}*/
</style>
