<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "App",
};
</script>

<style lang="scss">
$primary: hsl(241.21, 40.41%, 48.04%);
$primary2: hsl(240.53, 71.97%, 69.22%);
$primary3: hsl(223.2, 92.59%, 73.53%);
$primary--hover: hsl(241.21, 40.41%, 58.04%);
$primary2--hover: hsl(240.53, 71.97%, 79.22%);
$primary3--hover: hsl(223.2, 92.59%, 83.53%);
$font-primary: hsl(221.74, 9.62%, 46.86%);
$font-dark: black;
$bg-col-app: hsl(228, 100%, 98.04%);
$bg-col-item: white;
$tr--odd: hsl(0, 0%, 94.9%);

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Poppins", sans-serif;
}

#app {
  max-width: 100vw;
  min-height: 100vh;
  // background-color: salmon;
}

input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
</style>
