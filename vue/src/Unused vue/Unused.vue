<template>
  <div></div>
<!--  <RecipeDetailPage v-if="!singleRecipe" v-for="recipeDto in this.allRecipe" :key="recipeDto" :recipe-dto="recipeDto"/>-->
<!--  <RecipeDetailPageSingle v-if="singleRecipe" :recipe-dto="singleRecipeObject"/>-->
  <!-- Recipe Names List -->
<!--  <div class="input-group">-->
<!--    <h3>Recipes</h3>-->
<!--    <div class="recipeNameGroup">-->
<!--      <input id="recipeName" type="text" v-model="item.recipeName"-->
<!--             list="recipeNames"-->
<!--             placeholder="Recipe Name">-->
<!--      <datalist id="recipeNames">-->
<!--        <option v-for="itemName in recipeNamesToCheck" :key="itemName" :value="itemName"></option>-->
<!--      </datalist>-->
<!--      <button type="button" @click="addMeal">Add Recipe</button>-->
<!--    </div>-->

</template>

<script>

import RecipeDetailPage from "@/components/RecipeDetailPage.vue";
import RecipeDetailPageSingle from "@/Unused vue/RecipeDetailPageSingle.vue";

export default {
  components: {RecipeDetailPageSingle, RecipeDetailPage},
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
      // formData: {
      //   ingredients: [
      //     { name: '', quantity: null, unit: '' }
      //   ],
      //   recipeName: "",
      //   description: "",
      //   preparationTime: {
      //     days:0,
      //     hours:0,
      //     minutes:0
      //   },
      //   cookingTime: {
      //     days:0,
      //     hours:0,
      //     minutes:0
      //   },
      //   servings: null,
      //   difficultyLevel: "Easy", // Default value
      //   category: "Appetizer", // Default value
      //   instructions: "",
      //   tagsKeywords: "",
      //   authorNotes: "",
      //   sourceCredits: ""
      // },

      //
      // Ingredients: {
      //   "Name": "The name of each ingredient.",
      //   "Quantity": "The specific amount needed (e.g., 1 cup, 2 teaspoons).",
      //   "Unit": "The measurement unit for the quantity (e.g., cups, grams, tablespoons)."
      // },
    }
  },
  methods: {
    submitForm() {
      // let warning = "The following needs attention:"
      // let warn = false
      // for(let each in this.formData){
      //   let item = this.formData[each]
      //   if(typeof item == "string" && item.length === 0){
      //     warn = true
      //     let addon = `\n${each}\n`
      //     warning += addon
      //   }

      // }
      // console.log(warning)
      // if(warn){
      //   alert(warning)
      // }
      // },
      // addIngredient() {
      //   this.formData.ingredients.push({ name: '', quantity: null, unit: '' });
      // },
      // removeIngredient(index) {
      //   this.formData.ingredients.splice(index, 1);
      // },
    }

  }
}

</script>

<style scoped>

</style>