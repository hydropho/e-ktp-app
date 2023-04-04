<template>
  <div>
    <div class="header">
      <h1>Home</h1>
      <p>Dashboard</p>
    </div>

    <div class="home-content">
      <Card />
      <Table />
    </div>
  </div>
</template>

<script>
import Card from "../components/HomeCard.vue";
import Table from "../components/HomeTable.vue";
export default {
  name: "HomePage",
  components: {
    Card,
    Table,
  },
};
</script>

<style lang="scss" scoped>
.header {
  margin-bottom: 2rem;
}

.home-content {
  display: grid;
  grid-template-rows: 1fr 4fr;
  row-gap: 2rem;
}
</style>