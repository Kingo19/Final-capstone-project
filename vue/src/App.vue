<template>
  <div id="capstone-app">
    <header-view></header-view>
    <router-view/>
   <footer-view/>
  </div>
</template>
<script setup>
import HeaderView from "@/views/HeaderView.vue";
import FooterView from "./views/FooterView.vue";
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