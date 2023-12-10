<template>
  <div id="capstone-app">
    <header-view></header-view>
    <router-view/>
  </div>
</template>
<script setup>
import HeaderView from "@/views/HeaderView.vue";
</script>