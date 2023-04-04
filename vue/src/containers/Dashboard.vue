<template>
  <div class="container">
    <Sidebar class="sidebar" />
    <router-view class="content"></router-view>
  </div>
</template>

<script>
import Sidebar from "../components/Sidebar";
export default {
  name: "DashboardContainer",
  components: {
    Sidebar,
  },
};
</script>

<style lang="scss" scoped>
.container {
  min-height: 100vh;
  width: 100%;
  display: flex;
  padding: 1rem;
  padding-right: 2rem;

  background-color: hsl(228, 100%, 98.04%);

  .content {
    margin-left: calc(19.5rem);
    flex: 1 1 0;

    @media (max-width: 768px) {
      margin-left: 2rem;
      padding-left: 6rem;
    }
  }
}
</style>