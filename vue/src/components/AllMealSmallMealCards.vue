
<template>
  <div class="innerBody week-calendar">
    <div class="day-card" v-for="(day, index) in dayNames" :key="index" @click="selectDay(index)">
      <router-link :to="{ name: 'ViewMealPlanNum', params: { dayNumber: getMealPlanByDate(index) }}">
        <div>
        <h3>{{ day }}</h3>
        <p>{{ getMealPlanByDate(index) }}</p>
        </div>
      </router-link>
    </div>

    <div class="dateObj">
      <h1>You may also select a date</h1>
      <input type="date" @change="onDateChange" class="date-picker" />
    </div>

  </div>
</template>




<!--<template>
  <div class="week-calendar" >
    <router-link
        :to="{ name: 'ViewMealPlanNum',  params: { dayNumber: daynum } }"
        class="day-card"
        tag="div"
    >
      <div class="cont_photo">
          <h3>{{ name }}</h3>
          <p>{{ daynum }}</p>
        </div>
    </router-link>
  </div>
  <input type="date" @change="onDateChange" class="date-picker" />
</template>-->

<script>

import selectedMealPlan from "@/components/SelectedMealPlan.vue";

export default {

  props: ["daynum"],
  data() {
    return {
      dayNames: ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]
    };
  },
  methods:{

    selectDay(dayIndex) {
    },
    onDateChange(event) {
      const selectedDate = event.target.value;
      this.$router.push(`/ViewMealPlan/${selectedDate}`)
    },

    getMealPlanByDate(number){
      var targetDate = new Date();
      let newdate = targetDate.setDate(targetDate.getDate() + Number(number));
      let day = targetDate.getDate();
      let month = targetDate.getMonth() + 1;
      let year = targetDate.getFullYear();
      return `${year}-${month}-${day}`
    },


  }
};
</script>

<style scoped>

.week-calendar {
  background: url("../assets/plan.png");
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  gap: 10px;
  margin-bottom: 20px;
}







.day-card {
  width: calc(85% / 7);
  cursor: pointer;
  background-color: #ffffff;
  color: #000000;
  text-align: center;
  padding: 5px;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.day-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 6px 8px rgba(0, 0, 0, 0.3);
}

.cont_photo h3, .cont_photo p {
  margin: 5px 0;
}

.date-picker {
  display: block;
  background-color: #ffffff;
  color: #000000;
  text-align: center;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
  margin: 0 auto;
  padding: 10px;
  font-size: 1rem;
}



.dateObj{
  display: block;
  background-color: #ffffff;
  color: #000000;
  text-align: center;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
  margin: 0 auto;
  padding: 10px;
  font-size: 1rem;

}


/*
.week-calendar {
  display: flex;
  justify-content: center;
  gap: 2px;
  height: 1024px;
}

.day-card {
  width: 18%;
  height: 100px;
  cursor: pointer;
  background-color: #ffffff;
  color: #4caf50;
  text-align: center;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.day-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 6px 8px rgba(0, 0, 0, 0.3);
}

.cont_photo h3, .cont_photo p {
  margin: 5px 0;
}

.dateObj{
  justify-self: center;
  align-content: flex-end;


}


.date-picker {
  display: block;
  margin: 0 auto;
!*  padding: 10px;
  border-radius: 8px;*!
  border: 1px solid #4CAF50;
  font-size: 1rem;
}


*/

/*
.card-container {
  width: calc(90% / 7);
  justify-content: center;
!*  width: calc(30% - 20px); !* Adjusts card width *!*!
  background-color: #0056b3;
  margin: 2px;
}
.cardRecipe {
  display: block;
  cursor: pointer; !* Indicates the item is clickable *!
  height: 300px;
  margin: 10px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  overflow: hidden; !* Ensures that content exceeding the height is hidden *!
!*  position: relative; !* Required for absolute positioning of children *!*!
}

.cont_photo img {
  width: 100%;
  height: 100%;
  border-radius: 5px;
  object-fit: cover; !* Ensures the image covers the area without distortion *!
  max-height: 200px; !* Adjust the height as needed *!
  overflow: hidden; !* Ensures that content exceeding the height is hidden *!
}

.cont_detalles {
  position: absolute;
  width: 100%;
  bottom: 0;
  left: 0;
  right: 0;
  transition: all 0.5s ease; !* Smooth transition for expanding effect *!
}

.cont_detalles {
  background: rgba(0, 0, 0, 0.6);
  color: white;
  padding: 10px;
}

!* This will expand the ingredients section *!
.cardRecipe:hover .cont_text_ingredients {
  max-height: 200px; !* Adjust this value based on content *!
}*/

</style>