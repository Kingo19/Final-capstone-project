<template>
  <div class="container">
    <div class="card-container">
      <div class="card">
        <div class="card-image">
      <img src="https://s-media-cache-ak0.pinimg.com/736x/57/98/9f/57989f2a2e186e38bf93429aa395120c.jpg" alt="Recipe Image" />
        </div>

<!--
        <div class="card-details">
          <h2>{{ meal_plan.planName }}</h2>
          <div class="cont_tabs">
            <ul>
              <li v-for="tab in Tabs" :key="tab">
                <button @click="selectTab(tab)">{{ tab }}</button>
              </li>
            </ul>
          </div>

          <div v-for="tab in Tabs" :key="tab" v-show="activeTab === tab">
            <ul>
              <li v-for="item in this.getRecipeInfo(tab).recipeInfo" :key="item.recipe.recipe_name" @click="check(item)">
                <ul>
                    <button class="collapsible" @click="toggleCollapse(item.recipe.recipe_name)">{{ item.recipe.recipe_name }}</button>
                    <div class="content" v-if="isCollapsed">
                      <ul>
                      <li>{{ item.recipe.recipe_instructions }}</li>
                        <ul>
                        <li v-for="name in item.ingredients" :key="name.ingredient_name">{{ name.ingredient_name }}</li>
                      </ul></ul>
                    </div>
                </ul>
              </li>
            </ul>
          </div>
        </div>
-->

                <div class="card-details">
                  <h2>{{ meal_plan.planName }}</h2>
                  <div class="cont_tabs">
                    <ul>
                      <li v-for="tab in Tabs" :key="tab">
                        <button @click="selectTab(tab)">{{ tab }}</button>
                      </li>
                    </ul>
                  </div>

                  <div  v-for="tab in Tabs" :key="tab" v-show="activeTab === tab">
                    <ul>
                      <li v-for="item in this.getRecipeInfo(tab).recipeInfo" :key="item" @click="check(item)">
                        <ul>
                          <li>{{ item.recipe.recipe_name }}</li>
                          <li>{{ item.recipe.recipe_instructions }}</li>
                          <ul>
                            <li  v-for="name in item.ingredients" :key="name.ingredient_name">{{ name.ingredient_name }}</li>
                          </ul>
                        </ul>
                      </li>
                    </ul>
                  </div>
                </div>



<!--      <div>
        <button class="collapsible" @click="toggleCollapse">{{ title }}</button>
        <div class="content" v-show="isCollapsed">
          <slot></slot>
        </div>
      </div>-->












<!--          <div class="ingredients" v-show="activeTab === 'ingredients'">
            <h3>Ingredients</h3>
            <ul>
              <li v-for="ingredient in recipeDto.ingredients"  :key="ingredient"> {{ ingredient.ingredient_name }}</li>
            </ul>
          </div>

          <div class="instructions" v-show="activeTab === 'instructions'">
            <h3>Instructions</h3>
            <p>{{ recipeDto.recipe.recipe_instructions}}</p>
          </div>-->

          <div  class="edit" v-show="activeTab === 'edit'">
            <form id="form"  v-on:submit.prevent="submitForm"  class="formClass">
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
        </div>
    </div>
  </div>

</template>

<script>

import foodarray from "@/resources/foodNameArray";
import recipeService from "@/services/RecipeService";
import RecipeService from "@/services/RecipeService";

export default {
  computed:{
    Tabs(){
      return this.meal_plan.mealTimes.map(cu => cu.mealName)
    }
  },


  data() {
    return {
      collapsedStates: {},
      isCollapsed: false,
      recipeDto: {
        recipe:{
          recipe_name:"",
          recipe_instructions: ""
        },ingredients:[]
      },
      activeTab: '',
      recipeTestObj: {
        id: 1,
        name: 'Shakshuka With Feta',
        image: 'https://s-media-cache-ak0.pinimg.com/736x/57/98/9f/57989f2a2e186e38bf93429aa395120c.jpg',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit...',
        ingredients: ['Ingredient 1', 'Ingredient 2', 'Ingredient 3'],
        instructions: 'Heat oven to 375 degrees. Heat oil in a large skillet...',
      },
      addButton: "Recipe",
      mealData: {
        mealName: "",
        recipeNames: [],
        type: "",
      },
      types:["Breakfast","Lunch","Dinner","Appetizer","Salad","Main-course","Side-dish","Baked-goods","Dessert",
        "Snack","Soup","Holiday", "Vegetarian"],
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
      meal_plan:
          {
            "planId": 40,
            "planName": "TESTPA  SS112",
            "mealTimes": [
              {
                "mealId": 1,
                "mealName": "Salad",
                "mealTime": null,
                "recipeInfo": [
                  {
                    "ingredients": [
                      {
                        "ingredient_name": "TestIng11"
                      },
                      {
                        "ingredient_name": "TestIng789"
                      },
                      {
                        "ingredient_name": "TestIng456"
                      },
                      {
                        "ingredient_name": "TestIng123"
                      }
                    ],
                    "recipe": {
                      "recipe_name": "Test1",
                      "recipe_instructions": "Instructions"
                    }
                  },
                  {
                    "ingredients": [
                      {
                        "ingredient_name": "TestIng11"
                      },
                      {
                        "ingredient_name": "TestIng789"
                      },
                      {
                        "ingredient_name": "TestIng456"
                      },
                      {
                        "ingredient_name": "TestIng123"
                      }
                    ],
                    "recipe": {
                      "recipe_name": "Test2",
                      "recipe_instructions": "Instructions"
                    }
                  },
                  {
                    "ingredients": [
                      {
                        "ingredient_name": "TestIng11"
                      },
                      {
                        "ingredient_name": "TestIng789"
                      },
                      {
                        "ingredient_name": "TestIng456"
                      },
                      {
                        "ingredient_name": "TestIng123"
                      }
                    ],
                    "recipe": {
                      "recipe_name": "Test3",
                      "recipe_instructions": "Instructions"
                    }
                  }
                ],
                "type": "Salad"
              },
              {
                "mealId": 2,
                "mealName": "Pasta",
                "mealTime": null,
                "recipeInfo": [
                  {
                    "ingredients": [
                      {
                        "ingredient_name": "TestIng11"
                      },
                      {
                        "ingredient_name": "TestIng789"
                      },
                      {
                        "ingredient_name": "TestIng456"
                      },
                      {
                        "ingredient_name": "TestIng123"
                      }
                    ],
                    "recipe": {
                      "recipe_name": "Test4",
                      "recipe_instructions": "Instructions"
                    }
                  },
                  {
                    "ingredients": [
                      {
                        "ingredient_name": "TestIng11"
                      },
                      {
                        "ingredient_name": "TestIng789"
                      },
                      {
                        "ingredient_name": "TestIng456"
                      },
                      {
                        "ingredient_name": "TestIng123"
                      }
                    ],
                    "recipe": {
                      "recipe_name": "Test5",
                      "recipe_instructions": "Instructions"
                    }
                  },
                  {
                    "ingredients": [
                      {
                        "ingredient_name": "TestIng11"
                      },
                      {
                        "ingredient_name": "TestIng789"
                      },
                      {
                        "ingredient_name": "TestIng456"
                      },
                      {
                        "ingredient_name": "TestIng123"
                      }
                    ],
                    "recipe": {
                      "recipe_name": "Test6",
                      "recipe_instructions": "Instructions"
                    }
                  }
                ],
                "type": "Dinner"
              },
              {
                "mealId": 2,
                "mealName": "Pasta",
                "mealTime": null,
                "recipeInfo": [
                  {
                    "ingredients": [
                      {
                        "ingredient_name": "TestIng11"
                      },
                      {
                        "ingredient_name": "TestIng789"
                      },
                      {
                        "ingredient_name": "TestIng456"
                      },
                      {
                        "ingredient_name": "TestIng123"
                      }
                    ],
                    "recipe": {
                      "recipe_name": "Test4",
                      "recipe_instructions": "Instructions"
                    }
                  },
                  {
                    "ingredients": [
                      {
                        "ingredient_name": "TestIng11"
                      },
                      {
                        "ingredient_name": "TestIng789"
                      },
                      {
                        "ingredient_name": "TestIng456"
                      },
                      {
                        "ingredient_name": "TestIng123"
                      }
                    ],
                    "recipe": {
                      "recipe_name": "Test5",
                      "recipe_instructions": "Instructions"
                    }
                  },
                  {
                    "ingredients": [
                      {
                        "ingredient_name": "TestIng11"
                      },
                      {
                        "ingredient_name": "TestIng789"
                      },
                      {
                        "ingredient_name": "TestIng456"
                      },
                      {
                        "ingredient_name": "TestIng123"
                      }
                    ],
                    "recipe": {
                      "recipe_name": "Test6",
                      "recipe_instructions": "Instructions"
                    }
                  }
                ],
                "type": "Dinner"
              }
            ],
            "userId": 3,
            "dateOfPlan": "2023-12-03"
          }

    };
  },

  props: {
    title: String,
  },


  methods: {

    toggleCollapse() {
      this.isCollapsed = !this.isCollapsed;
    },

    selectTab(tab) {
      this.activeTab = tab;
    },

    submitForm() {
      RecipeService.modifyRecipeAndIngredients(this.formData.recipe.recipe_name, this.formData);
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

    checkRequiredNames() {
      return !this.isNameInDatabase() &&
          this.formData.recipe.recipe_name.length > 1;
    },

    toggleModal(id) {
      this.isModalOpen[id] = !this.isModalOpen[id];
    },

    getMealNames() {
      let mealNames = this.meal_plan.mealTimes.map(cu => cu.mealName)
      mealNames.push("edit")
    },

    getRecipeInfo(name) {
      let mealObj = {}
      this.meal_plan.mealTimes.forEach(cu => {
        if (cu.mealName === name) {
          mealObj = cu
        }
      })
      return mealObj
    },

    check(name) {
      console.log("Trigger")
      console.log(name)
    }
  }
}
</script>

<style scoped>



.top-level-remove-div{
  width: 100%;
  overflow: scroll;
}

.container{
  width: 1024px;
  height: 1024px;
  /*  background: url(https://druryjeff.github.io/better-from-the-source/img/wood.jpg) 50% 50%;*/
  display: flex;
  align-content: center;
  padding: 50px;
  font-size: 1.2em;
}

.card-container {
  width: 100%;
  height: 100%;

  margin: auto;
}

.card {
  display: flex;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
}

.card-image {
  width: 50%;
}

.card-image img {
  width: 100%;
  height: 100%;
}

.card-details {
  width: 50%;
  padding: 10px;
  background: linear-gradient(to bottom, rgba(251,249,249,1) 28%, rgba(232,234,237,1) 100%);
}

.cont_tabs {
  width: 100%;
  display: flex;
  justify-content: space-evenly;
}

.cont_tabs ul {
  display: flex;
  list-style-type: none;
  padding: 0;
}

.cont_tabs ul li {
  margin-right: 10px;
}

.cont_tabs ul li a {
  text-decoration: none;
  color: #241C3E;
  border-top: 7px solid #ED346C;
  display: block;
  padding: 10px;
}

.cont_tabs ul li a:hover {
  color: #ED346C;
}

/* Additional styles for Ingredients and Instructions sections */
.ingredients ul, .instructions {
  margin-top: 20px;
  font-size: 2em;
}


.formClass {
  font-size: 1em;
  border-radius: 10px;
  padding: 20px;
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

.collapsible {
  background-color: #f1f1f1;
  color: #444;
  cursor: pointer;
  padding: 18px;
  width: 100%;
  border: none;
  text-align: left;
  outline: none;
  font-size: 15px;
}

.active, .collapsible:hover {
  background-color: #ccc;
}

.content {
  padding: 0 18px;
  display: none;
  overflow: hidden;
  background-color: #f1f1f1;
}

</style>