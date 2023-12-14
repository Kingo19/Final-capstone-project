<template>
  <all-meal-small-meal-cards
      v-for="(day, index) in dayNames" :key="index" name="{{ day }}"
  :daynum="index + 1"></all-meal-small-meal-cards>
</template>

<script>
import allMealSmallMealCards from "@/components/AllMealSmallMealCards.vue";

export default {
  components:{
    allMealSmallMealCards
  },
  data(){
    return{
      dayNames: ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]
    }
  }
}
</script>

<style scoped>

</style>