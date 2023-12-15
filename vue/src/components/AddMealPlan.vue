<template>
  <div id="mealPlanApp" class="meal-plan-container">
    <form id="mealPlanForm" @submit.prevent="submitMealPlan" class="meal-plan-form">
      <!-- Meal Plan Name -->
      <div class="input-group">
        <label for="mealName">Meal Plan Name</label>
        <input type="text" id="mealName" v-model="mealPlanData.planName" required :maxlength="maxLenInput"
          placeholder="Enter meal plan name">
      </div>
      <!-- Meal Plan Date -->
      <div class="input-group">
        <label for="mealDate">Please select the sunday of your weekly plan</label>
        <input type="date" id="mealPlanDate" name="mealPlanDate" v-model="mealPlanData.date">
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
        planName: '',
        date: "",
        mealsWithTime: []
      }
    }
  },
  methods: {
    submitMealPlan() {
      // Add logic to submit meal plan data
      let MealPlanDateCheck = new Date(this.mealPlanData.date)
      console.log(MealPlanDateCheck)
      // this.isSunday = MealPlanDateCheck.getDay() === 6
      // if(this.isSunday) {

        console.log(this.mealPlanData);
        RecipeService.postMealPlan(this.mealPlanData)
        // }
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

      let mealRequestContainer = {mealId: selectedMeal.mealId, mealTime: ''}

      // add Meal Name to selectedMeal
      this.userMealNames.push(selectedMeal.mealName)
      this.mealPlanData.mealsWithTime.push(mealRequestContainer)


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
      let removeFromArray2 = this.mealPlanData.mealsWithTime
      let removedElement2 = removeFromArray1[index]
      removeFromArray2.splice(index, 1);
      //Assign it to meal object

      let removedMeal = {mealName: removedElement1, mealId: removedElement2}
      this.userMeals.push(removedMeal)
    }
  },
  mounted() {
    this.retrieveUserMeals()
  }
}
</script>

<style scoped>

#problem1 {
  color: red;
}

#mealPlanApp {
  background-image: url("../assets/meal plan 2.png");
  background-size: cover;
  display: flex;
  justify-content: center;
  align-content: center;
}

.meal-plan-container{
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin: 10px;
  min-height: 100vh;
}

.meal-plan-form{
  background-color: rgba(255, 248, 220, 0.8);
  border-radius: 10px;
  padding: 20px;
  position: relative;
  top: 100px;
  right: 30px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  width: 30%; /* Adjust the width as per your requirement */
  max-width: 800px; /* Set a max-width to ensure it doesn't get too wide on larger screens */
  height: auto;
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

.meal-item {
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
/*.meal-plan-container::before {
  content: "";
  position: absolute;
  top: 80px;
  width: 100%;
  height: 130%;
 !* background: linear-gradient(rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.2));*!
}*/

/*.submit-button:hover {
  background-color: #45A049;
}*/

/*@media (max-width: 768px) {
  .recipe-form {
    max-width: 90%;
  }
}*/

/*  background-color: #fff8dc;
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);*/



</style>