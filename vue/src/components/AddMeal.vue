<template>
  <div id="app" class="app-container">
    <form id="mealForm" @submit.prevent="submitMeal" class="formClass">
      <!-- Meal Name -->
      <div class="input-group">
        <h3>Meal Name</h3>
        <div class="name-group">
          <input
            type="text"
            id="mealname"
            v-model="mealData.mealName"
            list="mealNames"
            required
            :max="maxLenInput"
            placeholder="Enter meal name"
          />
          <datalist id="mealName">
            <option
              class="innerRecipeNames"
              v-for="itemRecipeName in this.recipeNamesToCheck"
              :key="itemRecipeName"
              :value="itemRecipeName"
            ></option>
          </datalist>
          <h2 id="problem1" v-if="isNameInDatabase">{{ badNamePrompt }}</h2>
        </div>
      </div>

      <!-- Meal Type -->
      <div class="input-group">
        <h3>Meal Type</h3>
        <select id="mealType" v-model="mealData.type">
          <option v-for="(eachType, num) in types" :key="num" :value="eachType">
            {{ eachType }}
          </option>
        </select>
      </div>

      <!--    Add Recipe into meal-->
      <div class="input-group">
        <h3>Add a recipe to your me</h3>
        <div class="add-items">
          <select
            :disabled="recipeNamesToCheck.length === 0"
            id="recipeName"
            v-model="item.recipeName"
          >
            <option
              class="recipe-item"
              v-for="(recipe, index) in recipeNamesToCheck"
              :key="index"
            >
              {{ recipe }}
            </option>
          </select>
          <button type="button" @click="addRecipe">Add Recipe</button>
        </div>
      </div>

      <!-- Remove items -->
      <div class="top-level-remove-div">
        <div
          class="show-list-added-items-or-remove"
          v-for="(recipe, index) in mealData.recipeNames"
          :key="index"
        >
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
      item: { recipeName: "" },
      maxLenInput: 255,
      mealData: {
        mealName: "",
        recipeNames: [],
        type: "",
      },
      types: [
        "Breakfast",
        "Brunch",
        "Lunch",
        "Dinner",
        "Appetizer",
        "Salad",
        "Main-course",
        "Side-dish",
        "Baked-goods",
        "Dessert",
        "Snack",
        "Soup",
        "Holiday",
        "Vegetarian",
      ],
    };
  },
  methods: {
    submitMeal() {
      console.log(this.mealData);
      let response = RecipeService.addMeal(this.mealData).then((cu) => {
        let save = cu.data;
      });

      this.mealData = {
        mealName: "",
        recipes: [],
        type: "",
      };
    },

    addRecipe(index) {
      let removeFromArray = this.recipeNamesToCheck;
      let removedElement = removeFromArray[index];
      removeFromArray.splice(index, 1);

      if (this.item.recipeName) {
        this.mealData.recipeNames.push(this.item.recipeName);
        this.item = { recipeName: "" };
      }
    },
    removeRecipe(index) {
      let removeFromArray = this.mealData.recipeNames;
      let removedElement = removeFromArray[index];
      removeFromArray.splice(index, 1);
      this.recipeNamesToCheck.push(removedElement.recipeName);
    },

    namesArrayCheck() {
      RecipeService.getUserRecipeNames().then((cu) => {
        this.recipeNamesToCheck = cu.data;
        console.log(this.recipeNamesToCheck);
      });
    },
  },
  mounted() {
    this.namesArrayCheck();
  },
};
</script>

<style scoped>
#problem1 {
  color: red;
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
  min-height: 100vh;
  background-image: url("https://images.unsplash.com/photo-1504674900247-0877df9cc836?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
  background-color: linear-gradient(rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.2));
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

input[type="text"],
select {
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
