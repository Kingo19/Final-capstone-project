<template>
  <form @submit.prevent="submitForm">

    <!-- Recipe Name -->
    <div class="input-group">
      <label for="recipeName">Recipe Name</label>
      <input type="text" id="recipeName" v-model="formData.recipeName"
             :aria-required="requiredFields.recipeName.toString()" maxlength="200" :placeholder="recipeFormFields['Recipe Name']">
    </div>

    <!-- Description -->
    <div class="input-group textfield">
      <label for="description">Description</label>
      <textarea :maxlength="maxlen" id="description" v-model="formData.description"
                :aria-required="requiredFields.description.toString()" rows="4" cols="50"
                :placeholder="recipeFormFields['Description']"></textarea>
    </div>

<!--    &lt;!&ndash; Preparation Time &ndash;&gt;-->
<!--    <div class="input-group">-->
<!--      <label for="preparationTime">Preparation Time</label>-->
<!--      <div class="time-inputs">-->
<!--        <input type="number" class="nums" v-model.number="formData.preparationTime.days" placeholder="Days" min="0">-->
<!--        <input type="number" class="nums" v-model.number="formData.preparationTime.hours" placeholder="Hours" min="0" max="23">-->
<!--        <input type="number" class="nums" v-model.number="formData.preparationTime.minutes" placeholder="Minutes" min="0" max="59">-->
<!--      </div>-->
<!--    </div>-->

<!--    &lt;!&ndash; Cooking Time &ndash;&gt;-->
<!--    <div class="input-group">-->
<!--      <label for="cookingTime">Cooking Time</label>-->
<!--      <div class="time-inputs">-->
<!--        <input type="number" class="nums" v-model.number="formData.cookingTime.days" placeholder="Days" min="0">-->
<!--        <input type="number" class="nums" v-model.number="formData.cookingTime.hours" placeholder="Hours" min="0" max="23">-->
<!--        <input type="number" class="nums" v-model.number="formData.cookingTime.minutes" placeholder="Minutes" min="0" max="59">-->
<!--      </div>-->
<!--    </div>-->

<!--    &lt;!&ndash; Servings &ndash;&gt;-->
<!--    <div class="input-group">-->
<!--      <label for="servings">Servings</label>-->
<!--      <input type="number" id="servings" v-model.number="formData.servings" :placeholder="recipeFormFields['Servings']" min="0">-->
<!--    </div>-->

<!--    &lt;!&ndash; Difficulty Level &ndash;&gt;-->
<!--    <div class="input-group">-->
<!--      <label for="difficultyLevel">Difficulty Level</label>-->
<!--      <select id="difficultyLevel" v-model="formData.difficultyLevel">-->
<!--        <option value="Easy">Easy</option>-->
<!--        <option value="Medium">Medium</option>-->
<!--        <option value="Hard">Hard</option>-->
<!--      </select>-->
<!--    </div>-->

<!--    &lt;!&ndash; Category &ndash;&gt;-->
<!--    <div class="input-group">-->
<!--      <label for="category">Category</label>-->
<!--      <select id="category" v-model="formData.category">-->
<!--        <option value="Snack">Snack</option>-->
<!--        <option value="Appetizer">Appetizer</option>-->
<!--        <option value="Main Course">Main Course</option>-->
<!--        <option value="Dessert">Dessert</option>-->
<!--      </select>-->
<!--    </div>-->

    <!-- Ingredients Section -->
    <!-- Area is placed in a div container for separation -->
    <div class="input-group">
      <h3>Ingredients test</h3>    <!-- Header -->
      <!-- Inner div - Each time cx adds an ingredient the page area will expand -->
      <!-- It will encompass the element in its own div -->
      <div class="ingredient-group" v-for="(ingredient, index) in formData.ingredients" :key="index">

        <!-- Text field -->
        <input :id="'ingredientName' + index" type="text" v-model="ingredient.name" list="ingredientNames" placeholder="Ingredient Name">
        <!-- Datalist is for predictive text. Array is used named foodName from foodNameArray.js -->
        <datalist id="ingredientNames">
          <option class="innerIngredient" v-for="itemName in foodName" :value="itemName" :key="itemName"></option>
        </datalist>
<!--        <input :id="'ingredientQuantity' + index" type="number" v-model.number="ingredient.quantity" placeholder="Quantity" min="0">-->
<!--        <input :id="'ingredientUnit' + index" type="text" v-model="ingredient.unit" placeholder="Unit (e.g., cups, grams)">-->
        <!-- Show's only that conversion -->
<!--        <select v-model="selectedUnitType">-->
<!--          <option value="metric">METRIC</option>-->
<!--          <option value="us">US</option>-->
<!--        </select>-->

<!--        <select v-show="selectedUnitType === 'metric'">-->
<!--          <option v-for="unit in metricUnits" :key="unit" :value="unit">{{ unit }}</option>-->
<!--        </select>-->

<!--        <select v-show="selectedUnitType === 'us'">-->
<!--          <option v-for="unit in usUnits" :key="unit" :value="unit">{{ unit }}</option>-->
<!--        </select>-->
      </div>
      <button type="button" @click="addIngredient">Add Ingredient</button>
      <button type="button" @click="removeIngredient(index)">Remove</button>
    </div>

<!--    &lt;!&ndash; Instructions &ndash;&gt;-->
<!--    <div class="input-group textfield">-->
<!--      <label for="instructions">Instructions</label>-->
<!--      <textarea :maxlength="maxlen" id="instructions" v-model="formData.instructions" :aria-required="requiredFields.instructions.toString()" rows="4" cols="50" :placeholder="recipeFormFields['Instructions']"></textarea>-->
<!--    </div>-->

<!--    &lt;!&ndash; Tags/Keywords &ndash;&gt;-->
<!--    <div class="input-group textfield">-->
<!--      <label for="tagsKeywords">Tags/Keywords</label>-->
<!--      <textarea :maxlength="maxlen" id="tagsKeywords" v-model="formData.tagsKeywords" :aria-required="requiredFields.tagsKeywords.toString()" rows="4" cols="50" :placeholder="recipeFormFields['tagsKeywords']"></textarea>-->
<!--    </div>-->

<!--    &lt;!&ndash; Author Notes &ndash;&gt;-->
<!--    <div class="input-group textfield">-->
<!--      <label for="authorNotes">Author Notes</label>-->
<!--      <textarea :maxlength="maxlen" id="authorNotes" v-model="formData.authorNotes" :aria-required="requiredFields.authorNotes.toString()" rows="4" cols="50" :placeholder="recipeFormFields['authorNotes']"></textarea>-->
<!--    </div>-->

<!--    &lt;!&ndash; Source/Credits &ndash;&gt;-->
<!--    <div class="input-group textfield">-->
<!--      <label for="sourceCredits">Source/Credits</label>-->
<!--      <textarea :maxlength="maxlen" id="sourceCredits" v-model="formData.sourceCredits" :aria-required="requiredFields.sourceCredits.toString()" rows="4" cols="50" :placeholder="recipeFormFields['sourceCredits']"></textarea>-->
<!--    </div>-->

    <div class="button-group">
      <button type="submit" class="submit-button" :disabled="!isFormValid">Submit</button>
    </div>
  </form>
</template>

<script>

import foodarray from "./foodNameArray"

export default {
  data() {
    return {

      requiredFields: {
        recipeName: true,
        description: true,
        preparationTime: true,
        cookingTime: true,
        servings: true,
        difficultyLevel: true,
        category: true,
        ingredients: true,
        instructions: true,
        tagsKeywords: true,
        authorNotes: true,
        sourceCredits: true
      },

      selectedUnitType: 'metric', // default selection
      metricUnits: ['milliliter', 'liter', 'gram', 'kilogram'],
      usUnits: ['teaspoon', 'tablespoon', 'cup', 'ounce', 'pound', 'quart', 'gallon'],
      maxlen:200,
      foodName: foodarray.fullFoodNameArray,
      formData: {
        ingredients: [
          { name: '', quantity: null, unit: '' }
        ],
        recipeName: "",
        description: "",
        preparationTime: {
          days:0,
          hours:0,
          minutes:0
        },
        cookingTime: {
          days:0,
          hours:0,
          minutes:0
        },
        servings: null,
        difficultyLevel: "Easy", // Default value
        category: "Appetizer", // Default value
        instructions: "",
        tagsKeywords: "",
        authorNotes: "",
        sourceCredits: ""
      },

      recipeFormFields: {
        "Recipe Name": "Name your recipe, we suggest something unique and easy to remember",
        "Description": "A brief description of the dish. ",
        "Preparation Time": "The time  necessary in order to prep to cook and or eat. ",
        "Cooking Time": "Separate from preparation time, this should focuses only on the actual cooking time and not be apart of the prep time.",
        "Servings": "Servings the recipe makes.",
        "Instructions": "Step-by-step directions on how to prepare and cook the dish.",
        "Difficulty Level": "An optional field to indicate how challenging the recipe is (e.g., Easy, Medium, Hard).",
        "Category": "The type of dish (e.g., Appetizer, Main Course, Dessert).",
        "Cuisine Type": "The cuisine style (e.g., Italian, Mexican).",
        "Tags/Keywords": "For easier searching and categorization (e.g., quick meals, summer recipes).",
        "Author Notes": "Additional tips or suggestions from the recipe's creator.",
        "Source/Credits": "If the recipe is adapted from another source, a field to give proper credit."
      },

      Ingredients: {
        "Name": "The name of each ingredient.",
        "Quantity": "The specific amount needed (e.g., 1 cup, 2 teaspoons).",
        "Unit": "The measurement unit for the quantity (e.g., cups, grams, tablespoons)."
      },
    }
  },
  methods: {

    submitForm() {
      let warning = "The following needs attention:"
      let warn = false
      for(let each in this.formData){
        let item = this.formData[each]
        if(typeof item == "string" && item.length === 0){
          warn = true
          let addon = `\n${each}\n`
          warning += addon
        }

      }
      console.log(warning)
      if(warn){
        alert(warning)
      }
    },
    addIngredient() {
      this.formData.ingredients.push({ name: '', quantity: null, unit: '' });
    },
    removeIngredient(index) {
      this.formData.ingredients.splice(index, 1);
    },

    getArrayOfKeywords() {
      let keywords = []
      let tags = this.formData.tagsKeywords.split(',');   // Split the initial string by commas
      for(let each of tags){  //Looping over that array to find words with spaces
        each = each.trim() //Trims the leading or trailing characters
        keywords.push(each)
        if(each.includes(" ")){ //Finds the spaces between words
          let temp = each.split(' ')
          for(let words of temp){
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
form {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
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

#main_option {
  width: 100%;
}

@media (max-width: 768px) {
  form {
    max-width: 90%; /* Smaller width on smaller screens */
  }
}
</style>
