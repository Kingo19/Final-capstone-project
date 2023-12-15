<template>
  <div class="container">
    <div class="card-container">
      <div class="card">
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

  data() {
    return {
      Tabs: [],
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
    },

    getMealPlanByDate(){
      let paramnum = this.$route.params.dayNumber
      var targetDate = new Date();
      let newdate = targetDate.setDate(targetDate.getDate() + Number(paramnum));
      console.log(newdate)
      let day = targetDate.getDate();
      let month = targetDate.getMonth() + 1;
      let year = targetDate.getFullYear();
      console.log(`${year}-${month}-${day}`)
      return `${year}-${month}-${day}`
    },

    getTodayDate(){
      const date = new Date();

      let day = date.getDate();
      let month = date.getMonth() + 1;
      let year = date.getFullYear();
// This arrangement can be altered based on how we want the date's format to appear.
      console.log(`${year}-${month}-${day}`)
      return `${year}-${month}-${day}`
    }

  },
  created() {
    recipeService.getDayPlan(this.getMealPlanByDate(this.$route.params.num)).then(cu => {

      console.log("trigger")
      console.log(cu.data)
      this.meal_plan = cu.data
      console.log(this.meal_plan)
      console.log(this.meal_plan.length)
      this.Tabs = this.meal_plan.mealTimes.map(cu => cu.mealName)

    })
  }
}
</script>

<style scoped>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 20px;
  font-family: 'Arial', sans-serif;
}

.card-container {
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  overflow: hidden;
  width: 80%;
  max-width: 800px;
}

.card-details h2 {
  background-color: #4CAF50;
  color: white;
  padding: 15px;
  margin: 0;
}

.cont_tabs ul {
  list-style-type: none;
  display: flex;
  padding: 0;
  margin: 0;
  background-color: #f2f2f2;
}

.cont_tabs li {
  flex: 1;
}

.cont_tabs button {
  width: 100%;
  padding: 10px;
  border: none;
  background-color: transparent;
  transition: background-color 0.3s;
  cursor: pointer;
}

.cont_tabs button:hover, .cont_tabs button:focus {
  background-color: #ddd;
}

.collapsible {
  background-color: #e7e7e7;
  color: #333;
  cursor: pointer;
  padding: 15px;
  border: none;
  text-align: left;
  width: 100%;
  transition: background-color 0.3s;
}

.collapsible:hover {
  background-color: #ddd;
}

.content {
  padding: 0 18px;
  background-color: white;
  display: none;
}

.content ul {
  list-style-type: none;
  padding: 0;
}

.content li {
  padding: 8px 0;
  border-bottom: 1px solid #ddd;
}

@media screen and (max-width: 768px) {
  .card-container {
    width: 90%;
  }
}
</style>