<template>
  <div class="card-container" >
    <router-link
        :to="{ name: 'ViewMealPlanNum',  params: { dayNumber: daynum } }"
        class="cardRecipe"
        tag="div"
    >
      <div class="cont_photo">
          <h3>{{ name }}</h3>
          <p>{{ daynum }}</p>
        </div>
    </router-link>
  </div>
</template>

<script>

import selectedMealPlan from "@/components/selectedMealPlan.vue";

export default {

  props: ["daynum"],
  data() {
    return {
      dayNames: ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]
    };
  },
  methods:{
    getDayAndDate(){

    },

    getMealPlanByDate(days){
      var targetDate = new Date();
      targetDate.setDate(targetDate.getDate() + days);
      let day = targetDate.getDate();
      let month = targetDate.getMonth() + 1;
      let year = targetDate.getFullYear();
      return `${year}-${month}-${day}`
    },


  }
};
</script>

<style scoped>


.card-container {
  width: calc(30% - 20px); /* Adjusts card width */
}
.cardRecipe {
  display: block;
  cursor: pointer; /* Indicates the item is clickable */
  height: 300px;
  margin: 10px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  overflow: hidden; /* Ensures that content exceeding the height is hidden */
  position: relative; /* Required for absolute positioning of children */
}

.cont_photo img {
  width: 100%;
  height: 100%;
  border-radius: 5px;
  object-fit: cover; /* Ensures the image covers the area without distortion */
  max-height: 200px; /* Adjust the height as needed */
  overflow: hidden; /* Ensures that content exceeding the height is hidden */
}

.cont_detalles {
  position: absolute;
  width: 100%;
  bottom: 0;
  left: 0;
  right: 0;
  transition: all 0.5s ease; /* Smooth transition for expanding effect */
}

.cont_detalles {
  background: rgba(0, 0, 0, 0.6);
  color: white;
  padding: 10px;
}

/* This will expand the ingredients section */
.cardRecipe:hover .cont_text_ingredients {
  max-height: 200px; /* Adjust this value based on content */
}

</style>