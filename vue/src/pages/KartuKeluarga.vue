<template>
  <div>
    <Toast :toastMsg="toastMsg" />
    <div class="header">
      <h1>Kartu Keluarga</h1>
      <p>Data kartu keluarga</p>
    </div>
    <div class="content">
      <div v-if="isShowingKK">
        <Detail
          v-if="isShowingDetail"
          @showTable="showTable"
          :sendId="sendId"
          @sendToastMsg="sendToastMsg"
          @showAnggotaKeluarga="showAnggotaKeluarga"
        />
        <Table @showDetail="showDetail" @sendToastMsg="sendToastMsg" v-else />
      </div>
      <div v-else>
        <AnggotaKeluargaTable
          :sendId="sendId"
          @sendToastMsg="sendToastMsg"
          @showDetail="showDetail"
        />
      </div>
    </div>
  </div>
</template>

<script>
import Table from "../components/KartuKeluargaTable.vue";
import Detail from "../components/KartuKeluargaDetail.vue";
import AnggotaKeluargaTable from "../components/AnggotaKeluargaTable";
import Toast from "../components/ToastMsg.vue";
export default {
  name: "KartuKeluargaPage",
  components: {
    Table,
    Detail,
    Toast,
    AnggotaKeluargaTable,
  },
  data() {
    return {
      isShowingDetail: false,
      toastMsg: {},
      sendId: null,
      isShowingKK: true,
    };
  },
  methods: {
    showDetail(id) {
      this.isShowingDetail = !this.isShowingDetail;
      this.sendId = id;
      this.isShowingKK = true;
    },
    sendToastMsg(data) {
      this.toastMsg = data;
    },
    showTable() {
      this.isShowingDetail = !this.isShowingDetail;
    },
    showAnggotaKeluarga(id_kk) {
      this.sendId = id_kk;
      this.isShowingKK = false;
    },
  },
};
</script>

<style lang="scss" scoped>
.header {
  margin-bottom: 1.5rem;
}
</style>