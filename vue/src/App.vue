<template>
<!--  <div id="capstone-app">-->
  <section class="layout">
    <header-view class="header"/>
    <router-view class="innerBody"/>
    <footer-view class="footer"/>
  </section>

<!--  </div>-->
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

/*width: 1920px;
height: 1080px;*/

.layout {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 1fr;
  grid-template-areas:
    "header header header"
    "innerBody . ."
    "footer footer footer";
  gap: 8px;
}

.header { grid-area: header; }
.leftSide { grid-area: leftSide; }
.innerBody { grid-area: innerBody; }
.rightSide { grid-area: rightSide; }
.footer {
  grid-area: footer;
  height: 50%;
}

</style>