<template>
  <div id="mealPlanApp" class="meal-plan-container">
    <form id="mealPlanForm" @submit.prevent="submitMealPlan" class="meal-plan-form">
      <!-- Meal Plan Name -->
      <div class="input-group">
        <label for="mealName">Meal Plan Name</label>
        <input type="text" id="mealName" v-model="mealPlanData.plan_name" required :maxlength="maxLenInput"
          placeholder="Enter meal plan name">
      </div>
      <!-- Meal Plan Date -->
      <div class="input-group">
        <label for="mealDate">Please select the sunday of your weekly plan</label>
        <input type="date" id="mealPlanDate" name="mealPlanDate" v-model="mealPlanData.mealPlanDate">
      </div>
      

      <!-- Meal List -->
      <div class="input-group">
        <h3>Add a meal to your plan</h3>
        <div class="meal-group">
        <label for="userMeals"></label>
        <select :disabled="userMeals.length === 0" id="mealName" v-model="item.mealName">
          <option class="meal-item" v-for="(meal, index) in userMeals" :key="index">{{ meal.mealName }}</option>
        </select>
        <button type="button" @click="addMeal">Add Meal</button>
        </div>
      </div>
      <!-- Remove Button -->
      <div class="top-level-remove-div">
        <div class="show-list-added-items-or-remove"  v-for="(name, index) in userMealNames" :key="index">
          <p>{{ name }}</p>
          <button type="button" @click="removeMeal(index)">Remove</button>
        </div>
      </div>

      <!-- Submit Button -->
      <div class="button-group">
        <button type="submit" class="submit-button">Submit Meal Plan</button>
      </div>
      <div>
        <p v-if="!isSunday">Please Select A Sunday!</p>
      </div>
    </form>
  </div>
</template>

<script>
import RecipeService from "@/services/RecipeService";

export default {
  data() {
    return {
      user: this.$store.state.user,
      userMeals: [],
      userMealNames: [],
      item: {mealName: ''},
      maxLenInput: 255,
      isSunday: true,
      mealPlanData: {
        plan_name: "",
        mealPlanDate: "",
        mealIds: []
      }
    }
  },
  methods: {
    submitMealPlan() {
      // Add logic to submit meal plan data
      let MealPlanDateCheck = new Date(this.mealPlanData.mealPlanDate)
      console.log(MealPlanDateCheck)
      this.isSunday = MealPlanDateCheck.getDay() === 6
      if(this.isSunday) {

        console.log(this.mealPlanData);
      }
      // Call a service to POST the data to your endpoint
    },
    retrieveUserMeals(){
      RecipeService.getUserMeals().then(cu => {
         this.userMeals = cu.data
      });
    },
    addMeal(index) {
      let selectedMeal = this.userMeals.find(value => {
        return value.mealName === this.item.mealName
      })
      console.log(selectedMeal)

      // add Meal Name to selectedMeal
      this.userMealNames.push(selectedMeal.mealName)
      this.mealPlanData.mealIds.push(selectedMeal.mealId)


      let removeFromArray = this.userMeals
      let removedElement = removeFromArray[index]
      removeFromArray.splice(index, 1);

      console.log(this.item.mealName)
      
      
    },
    removeMeal(index) {
      //remove Meal Name
      let removeFromArray1 = this.userMealNames
      let removedElement1 = removeFromArray1[index]
      removeFromArray1.splice(index, 1);
      //remove Meal Id
      let removeFromArray2 = this.userMealNames
      let removedElement2 = removeFromArray1[index]
      removeFromArray2.splice(index, 1);
      //Assign it to meal object

      let removedMeal = {mealName: removedElement1, mealId: removedElement2}
      this.userMeals.push(removedMeal)
    },

  },
  mounted() {
    this.retrieveUserMeals()
  }
}
</script>

<style scoped>
.meal-plan-container {
  font-family: Arial, sans-serif;
  background-color: rgb(239, 234, 231);
  min-height: 100vh;
  padding: 20px;
}

.meal-plan-form {
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

input[type='text'],
textarea,
select {
  width: 100%;
  padding: 5px;
  border: 2px solid #ccc;
  border-radius: 5px;
  font-size: 1em;
  color: #333;
}

.button-group {
  display: flex;
  justify-content: center;
  width: 100%;
}

.submit-button {
  background-color: #ffcc00;
  border: none;
  color: #333;
  padding: 10px 15px;
  text-transform: uppercase;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.submit-button:hover {
  background-color: #e6b800;
}

@media (max-width: 768px) {
  .meal-plan-form {
    max-width: 90%;
  }
}
</style>