<template>
  <div></div>

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

</template>

<script>

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