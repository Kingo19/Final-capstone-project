<template>
  <div id="capstone-app">
    <header-view></header-view>
    <router-view/>
  </div>
</template>
<script setup>
import HeaderView from "@/views/HeaderView.vue";
</script>

<style>

@font-face {
  font-family: "MV Boli";
  src: url("resources/mvboli.ttf");
}

body{
  font-family: "MV Boli";
}


</style>