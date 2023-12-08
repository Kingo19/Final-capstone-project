<template>
    <h1>{{ modify }}</h1>
    <h1>Testing</h1>
</template>

<script>
import foodarray from "@/resources/foodNameArray";
import RecipeService from "../services/RecipeService";

export default {
  props:{
    modify:Object
  },
}
</script>

<style scoped>

</style>
