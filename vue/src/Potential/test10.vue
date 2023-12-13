<template>
  <div class="week-view-container">
    <div class="week-header">
      <div class="day-header" v-for="day in days" :key="day">{{ day }}</div>
    </div>
    <div class="week-content">
      <div class="day-column" v-for="day in days" :key="day">
        <div class="time-slot" v-for="time in times" :key="time" @click="onTimeSlotClick(day, time)">
          {{ time }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SevenDayView",
  data() {
    return {
      days: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'],
      times: this.generateTimes(),
    };
  },
  methods: {
    generateTimes() {
      let times = [];
      for (let hour = 0; hour < 24; hour++) {
        ['00', '15', '30', '45'].forEach(minute => {
          times.push(`${hour.toString().padStart(2, '0')}:${minute}`);
        });
      }
      return times;
    },
    onTimeSlotClick(day, time) {
      console.log(`Clicked on ${day} at ${time}`);
      // Add your click event handling logic here
    },
  },
};
</script>

<style scoped>
.week-view-container {
  display: flex;
  flex-direction: column;
  max-width: 100%;
}

.week-header {
  display: flex;
}

.day-header {
  flex: 1;
  text-align: center;
  background-color: #f0f0f0;
  padding: 10px;
  border: 1px solid #ccc;
}

.week-content {
  display: flex;
  flex-grow: 1;
  color: white;
}

.day-column {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.time-slot {
  flex: 1;
  border: 1px solid #ccc;
  padding: 5px;
  text-align: center;
  cursor: pointer;
}

/* Custom media query for smaller screens */
@media (max-width: 768px) {
  .week-view-container {
    font-size: 14px;
  }
}
</style>


<!--
Even pictures and nice layout, will modify but should be good for single page layout

<template>
  <div class="card-container">
    <div class="card">
      <div class="card-image">
        <img src="https://s-media-cache-ak0.pinimg.com/736x/57/98/9f/57989f2a2e186e38bf93429aa395120c.jpg" alt="Recipe Image" />
      </div>
      <div class="card-details">
        <div class="cont_tabs">
          <ul>
            <li><a href="#">Ingredients</a></li>
            <li><a href="#">Instructions</a></li>
          </ul>
        </div>
        <div class="ingredients">
          <h3>Ingredients</h3>
          <ul>
            <li>Ingredient 1</li>
            <li>Ingredient 2</li>
            <li>Ingredient 3</li>
            &lt;!&ndash; Add more ingredients as needed &ndash;&gt;
          </ul>
        </div>
        <div class="instructions">
          <h3>Instructions</h3>
          <p>Heat oven to 375 degrees. Heat oil in a large skillet...</p>
          &lt;!&ndash; Add more instructions as needed &ndash;&gt;
        </div>
      </div>
    </div>
  </div>
</template>


<script>
export default {
  data() {
    return {
      recipes: [
        // Sample recipe data
        {
          id: 1,
          name: 'Shakshuka With Feta',
          image: 'https://s-media-cache-ak0.pinimg.com/736x/57/98/9f/57989f2a2e186e38bf93429aa395120c.jpg',
          description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit...',
          ingredients: ['Ingredient 1', 'Ingredient 2', 'Ingredient 3'],
          instructions: 'Heat oven to 375 degrees. Heat oil in a large skillet...'
        },
        // Add more recipes as needed
      ],
    };
  }
};
</script>
<style scoped>
.card-container {
  width: 100%;
  max-width: 800px;
  margin: auto;
}

.card {
  display: flex;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
}

.card-image {
  width: 40%;
}

.card-image img {
  width: 100%;
  height: 100%;
}

.card-details {
  width: 60%;
  padding: 10px;
  background: linear-gradient(to bottom, rgba(251,249,249,1) 28%, rgba(232,234,237,1) 100%);
}

.cont_tabs {
  width: 100%;
  display: flex;
  justify-content: space-between;
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
}
</style>
-->
