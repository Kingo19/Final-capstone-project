<template>
  <div class="recipe-page">
    <div class="recipe-card">
      <div class="recipe-image">
        <img src="https://s-media-cache-ak0.pinimg.com/736x/57/98/9f/57989f2a2e186e38bf93429aa395120c.jpg" alt="Shakshuka With Feta" />
        <div class="recipe-time">50 MINS</div>
        <div class="recipe-servings">4 SERVINGS</div>
      </div>
      <div class="recipe-content">
        <div class="recipe-title">Shakshuka With Feta</div>
        <div class="recipe-description">
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam sagittis est aliquam, sed faucibus massa lobortis. Maecenas non est justo.
        </div>
        <div class="recipe-tabs">
          <div class="tab active">INGREDIENTS</div>
          <div class="tab">PREPARATION</div>
        </div>
        <div class="recipe-details">
          <div class="ingredients-list">
            <h4>Ingredients</h4>
            <ul>
              <li>4 eggs</li>
              <li>1 can tomatoes</li>
              <li>1 onion</li>
              <!-- More ingredients -->
            </ul>
          </div>
          <div class="preparation-steps">
            <h4>Preparation</h4>
            <ol>
              <li>Heat oven to 375 degrees.</li>
              <li>Heat oil in a large skillet over medium-low heat. Add onion and bell pepper...</li>
              <!-- More steps -->
            </ol>
          </div>
        </div>
      </div>
    </div>
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

    getArrayOfNames(){
      console.log("getArrayOfNames")
      console.log(this.recipeNamesToCheck)
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
.recipe-page {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f3f2ef;
  padding: 2rem;
}

.recipe-card {
  width: 100%;
  max-width: 900px;
  display: flex;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  overflow: hidden;
}

.recipe-image {
  position: relative;
  width: 40%;
}

.recipe-image img {
  width: 100%;
  height: auto;
  display: block;
}

.recipe-time,
.recipe-servings {
  position: absolute;
  top: 20px;
  right: -50px;
  background: #ffd700;
  color: #fff;
  padding: 0.5rem 2rem;
  transform: rotate(45deg);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.recipe-servings {
  top: auto;
  bottom: 20px;
}

.recipe-content {
  width: 60%;
  padding: 2rem;
  background: #ffffff;
}

.recipe-title {
  font-size: 2rem;
  font-weight: bold;
  color: #333;
}

.recipe-description {
  margin-top: 1rem;
  font-size: 1rem;
  line-height: 1.5;
  color: #666;
}

.recipe-tabs {
  display: flex;
  margin-top: 2rem;
}

.tab {
  padding: 0.5rem 1rem;
  cursor: pointer;
  transition: background-color 0.3s, color 0.3s;
}

.tab.active {
  background-color: #333;
  color: #fff;
}

.recipe-details {
  margin-top: 2rem;
}

.ingredients-list, .preparation-steps {
  margin-bottom: 1rem;
}

h4 {
  font-size: 1.5rem;
  color: #333;
}

ul, ol {
  list-style: none;
  padding-left: 0;
}

li {
  font-size: 1rem;
  line-height: 1.5;
  color: #666;
}

@media (max-width: 768px) {
  .recipe-card {
    flex-direction: column;
  }

  .recipe-image,
  .recipe-content {
    width: 100%;
  }

  .recipe-time,
  .recipe-servings {
    right: 20px;
    transform: rotate(0);
  }
}
</style>
