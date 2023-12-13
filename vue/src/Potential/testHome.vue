<template>
  <div class="card-container">
    <cardRecipe></cardRecipe>
  </div>
</template>


<script>
import cardRecipe from "@/Potential/test4.vue";


export default {
  components: {
    cardRecipe,
  },
}


</script>

<style scoped>
.card-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  align-items: stretch;
  gap: 20px;
  margin: 20px;
}

.cardRecipe {
  flex-basis: calc(33.333% - 40px); /* Subtracting twice the gap size */
  flex-grow: 0;
  flex-shrink: 0;
  margin: 20px; /* Optional, for additional spacing */
  /* Add any additional styling needed for individual cards here */
}
</style>
