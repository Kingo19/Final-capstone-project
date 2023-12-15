<template>
  <div class="recipe-detail-container">
    <div v-if="!modifyRecipe" class="original-recipe">
      <div class="head-of-container">
        <h1>{{ recipeDto.recipe.recipe_name }}</h1>
      </div>

      <button @click="mod" v-if="!modifyRecipe">Modify Recipe</button>

      <div class="recipe-details">
        <img class="recipe-image"
             src="https://www.sandiegoprosthodontics.com/files/2018/09/Recipes-Banner.jpg"
             alt="Recipe Image"/>

        <p class="instructions">{{ recipeDto.recipe.recipe_instructions }}</p>

        <div class="top-level-remove-div">
          <strong>Ingredients:</strong>
          <ul>
            <li v-for="ingredient in recipeDto.ingredients" :key="ingredient">{{ ingredient.ingredient_name }}</li>
          </ul>
        </div>
      </div>
    </div>

    <!--    ===============================================================-->

    <div v-if="modifyRecipe" id="appAddMeal" class="app-containerMeal">
        <form id="form" v-on:submit.prevent="submitForm" class="formClass">
          <!-- Recipe Name -->
          <div class="input-group">
            <h3>Recipe Name</h3>
            <div class="name-group">
              <input type="text" id="recipeName" v-model="recipeDto.recipe.recipe_name"
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
                      id="description" v-model="recipeDto.recipe.recipe_instructions"
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
            <div class="show-list-added-items-or-remove" v-for="(ingredient, index) in recipeDto.ingredients" :key="index">
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


<!--    Closing of full container-->
  </div>
</template>

<script>
import foodarray from "@/resources/foodNameArray";
import RecipeService from "@/services/RecipeService";

export default {
  props: {
    recipeDto: Object
  },
  data(){
    return{
      modifiedProp:{},
      badNamePrompt:"You have entered a name already in our database. Please try again",
      recipeNamesToCheck: [],
      maxLenInput: 255,
      maxLenTextArea: 1999,
      originalName: "",
      modifyRecipe:false,
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
  methods:{
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

    mod(){
      this.modifyRecipe = !this.modifyRecipe
    },

    submitForm() {
      let newObject = {
        recipe:{
          "recipe_name": this.recipeDto.recipe_name,
          "recipe_instructions": this.recipeDto.recipe_instructions,
        },
        "ingredients": this.recipeDto.ingredients
      }
      RecipeService.modifyRecipeAndIngredients(this.originalName, this.recipeDto);
      this.mod()
      this.originalName = this.recipeDto.recipe.recipe_name
      this.$router.push("/all/recipe")
    },

    addIngredient() {
      if (this.item.ingredient_name) {
        this.recipeDto.ingredients.push({ ...this.item });
        this.item = { ingredient_name: '' };
      } else {
        alert("Ingredient name cannot be empty.");
      }
    },

    removeIngredient(index) {
      this.recipeDto.ingredients.splice(index, 1);
    },
  },

  computed: {
    checkIfFormIsValid() {
      return this.formData.recipe.recipe_name &&
          this.formData.recipe.recipe_instructions &&
          this.formData.ingredients.length > 0;
    },

    isNameInDatabase(){
      return this.recipeNamesToCheck.includes(this.formData.recipe.recipe_name)
    }

  },

  created() {
    this.originalName = this.recipeDto.recipe.recipe_name
/*    for(var k in firstObject) secondObject[k]=firstObject[k];*/
    this.modifiedProp = Object.assign({}, this.recipeDto)
  }
};

</script>

<style scoped>
/* Add styling for recipe detail view */
.recipe-detail-container {
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1); /* Add box shadow */
  border-radius: 8px; /* Add border-radius for a rounded appearance */
}

.head-of-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

h1 {
  margin: 0;
  font-size: 2em;
  color: #333;
}

.back-link {
  text-decoration: none;
  color: #007BFF;
  cursor: pointer;
  display: inline-block;
}

.back-icon {
  margin-right: 5px;
}

.recipe-details {
  margin-top: 20px;
}

.recipe-image {
  max-width: 100%;
  height: auto;
  border-radius: 8px;
  margin-bottom: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); /* Add box shadow for the image */
}

.instructions {
  font-size: 1.2em;
  line-height: 1.6;
  color: #555;
}

.top-level-remove-div {
  margin-top: 20px;
}

.top-level-remove-div strong {
  display: block;
  margin-bottom: 10px;
  font-size: 1.2em;
  color: #333;
}

.top-level-remove-div ul {
  padding: 0;
}

.top-level-remove-div li {
  margin-bottom: 8px;
  font-size: 1em;
  color: #555;
}











#problem1{
  color:red;
}

.app-containerMeal {
  /*  background-color: rgb(239 234 231);*/
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
/*  width: calc(33% - 20px); !* Adjusts card width *!*/
  margin: 10px;
}

.formClass {
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

/*.submit-button:hover {
  background-color: #45A049;
}*/

@media (max-width: 768px) {
  .formClass {
    max-width: 90%;
  }
}
</style>
