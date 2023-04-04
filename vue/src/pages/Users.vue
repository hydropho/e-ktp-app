<template>
  <div>
    <Toast :toastMsg="toastMsg" />
    <div class="header">
      <h1>Users</h1>
      <p>Data users</p>
    </div>
    <div class="content">
      <Table @sendToastMsg="sendToastMsg" />
    </div>
  </div>
</template>

<script>
import Table from "../components/UsersTable.vue";
import Toast from "../components/ToastMsg.vue";
export default {
  name: "PendudukPage",
  components: {
    Table,
    Toast,
  },
  data() {
    return {
      toastMsg: {},
    };
  },
  methods: {
    sendToastMsg(data) {
      this.toastMsg = data;
    },
  },
};
</script>

<style lang="scss" scoped>
.header {
  margin-bottom: 1.5rem;
}
</style>