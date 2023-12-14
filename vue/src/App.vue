<template>
  <section class="layout">
    <header-view class="header"/>
    <router-view class="innerBody"/>
    <footer-view class="footer"/>
  </section>
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
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}



.layout {
  display: grid;
  grid-template-areas:
    "header"
    "innerBody"
    "footer";
  gap: 8px;
}

.header {
  grid-area: header;
}
.innerBody {
  grid-area: innerBody;
  background: url(https://druryjeff.github.io/better-from-the-source/img/wood.jpg) 50% 50%;
}
.footer {
  grid-area: footer;
  max-height: 200px;
}

</style>