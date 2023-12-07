<template>
  <div id="test" class="test">
    <form id="form" @submit.prevent="submitForm">

      <!-- Recipe Name -->
      <div class="input-group">
        <label for="recipeName">Recipe Name</label>
        <input type="text" id="recipeName" v-model="formData.recipe.recipe_name"
               :aria-required="requiredFields.recipeName.toString()"
               maxlength="200"
               placeholder="Name your recipe, we suggest something unique and easy to remember">
      </div>

      <!-- Description -->
      <div class="input-group textfield">
        <label for="description">Description</label>
        <textarea :maxlength="maxlen" id="description" v-model="formData.recipe.recipe_instructions"
                  :aria-required="requiredFields.instructions.toString()"
                  rows="4" cols="50"
                  placeholder="A brief description of the dish."></textarea>
      </div>

      <!-- Ingredients Section -->
      <div class="input-group">
        <h3>Ingredients test</h3>    <!-- Header -->
        <div class="ingredient-group" v-for="(ingredient, index) in formData.ingredients" :key="index">
          <input :id="'ingredientName' + index" type="text" v-model="ingredient.ingredient_name" list="ingredientNames"
                 placeholder="Ingredient Name">
          <!-- Datalist is for predictive text. Array is used named foodName from foodNameArray.js -->
          <datalist id="ingredientNames">
            <option class="innerIngredient" v-for="itemName in foodName" :value="itemName" :key="itemName"></option>
          </datalist>
        </div>
        <button type="button" @click="addIngredient">Add Ingredient</button>
        <button type="button" @click="removeIngredient(index)">Remove</button>
      </div>
      <div class="button-group">
        <button type="submit" class="submit-button" :disabled="!isFormValid">Submit</button>
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
      recipe_name: "Name your recipe, we suggest something unique and easy to remember",
      Instructions: "How to prepare your amazing recipe. ",
      maxlen: 200,
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
      RecipeService.addRecipeAndIngredients(this.formData)
    },

    addIngredient() {
      this.formData.ingredients.push({ingredient_name: ''});
    },
    removeIngredient(index) {
      this.formData.ingredients.splice(index, 1);
    },

    getArrayOfKeywords() {
      let keywords = []
      let tags = this.recipe.tagsKeywords.split(',');   // Split the initial string by commas
      for (let each of tags) {  //Looping over that array to find words with spaces
        each = each.trim() //Trims the leading or trailing characters
        keywords.push(each)
        if (each.includes(" ")) { //Finds the spaces between words
          let temp = each.split(' ')
          for (let words of temp) {
            keywords.push(words.trim())
          } //end of forloop words
        } //end of if includes
      } // end of main - each - for loop
      console.log(keywords)
      return keywords;
    }, //Function end
  },
  computed: {
    isFormValid() {
      return true;
    }
  }
}
</script>


<style scoped>

@font-face {
  font-family: "MV Boli";
  src: url("../resources/mvboli.ttf");
}

#test {
  background-color: rgb(239 234 231);
}

form {
  font-family: "MV Boli";
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  max-width: 600px; /* Adjust the width of the form. Perhaps modify to allow reactivity*/
  margin: 0 auto; /* Center the form horizontally */
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); /* Adds a subtle shadow around the form */
  background-color: #fff; /* Background color for the form. Starting color #fff*/
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
  color: #333; /* Darker label color for better readability */
}

input[type='text'], input[type='number'], select, textarea {
  width: 100%;
  padding: 5px;
  border: 2px solid #ccc;
  border-radius: 5px;
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
  font-size: 1em;
  color: #333;
}

input.nums {
  width: 20%;
}

select {
  cursor: pointer;
}

.button-group {
  display: flex;
  justify-content: center;
  width: 100%;
}

.submit-button {
  background-color: #4CAF50; /* Green color for the submit button */
  color: white;
  padding: 12px 24px;
  border: none;
  border-radius: 5px;
  font-size: 1.2em;
  cursor: pointer;
  transition: background-color 0.3s ease;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
}

.submit-button:hover {
  background-color: #45A049; /* Darker shade on hover */
}

.ingredient-group {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

@media (max-width: 768px) {
  form {
    max-width: 90%; /* Smaller width on smaller screens */
  }
}
</style>
