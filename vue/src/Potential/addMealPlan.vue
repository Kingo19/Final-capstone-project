<template>
  <div id="mealPlanApp" class="meal-plan-container">
    <form id="mealPlanForm" @submit.prevent="submitMealPlan" class="meal-plan-form">
      <!-- Meal Plan Name -->
      <div class="input-group">
        <label for="mealName">Meal Name</label>
        <input type="text" id="mealName" v-model="mealPlanData.plan_name"
               required
               :maxlength="maxLenInput"
               placeholder="Enter meal plan name">
      </div>
      <option v-for="(eachType, num) in types" :key="num"  :value="eachType">{{ eachType }}</option>
      <!-- Meal Type -->
      <div class="input-group">
        <label for="mealType">Meal Type</label>
        <select id="mealType" v-model="mealPlanData.type">
          <option v-for="(eachType, num) in types" :key="num"  :value="eachType">{{ eachType }}</option>
        </select>
      </div>

      <!-- Meal Names -->
      <div class="input-group">
        <label for="mealNames">Meal Names</label>
        <textarea id="mealNames" v-model="mealPlanData.mealNames"
                  required
                  rows="3" cols="50"
                  placeholder="Enter meal names, separated by commas"></textarea>
      </div>

      <!-- Submit Button -->
      <div class="button-group">
        <button type="submit" class="submit-button">Submit Meal Plan</button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      maxLenInput: 255,
      mealPlanData: {
        plan_name: "",
        mealNames: "",
        type: ""
      },
      types:[
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
        "Vegetarian"
      ]

    }
  },
  methods: {
    submitMealPlan() {
      // Add logic to submit meal plan data
      console.log(this.mealPlanData);
      // Call a service to POST the data to your endpoint
    }
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

input[type='text'], textarea, select {
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