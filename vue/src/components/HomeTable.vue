<template>
  <div class="table-container">
    <div class="table-card">
      <div class="table-header">
        <h1 class="table-title">Data Kartu Keluarga</h1>
        <p class="table-desc">
          <span class="red">*</span> Menampilkan 5 data teratas
        </p>
      </div>
      <div class="table-content">
        <table>
          <thead>
            <td>No</td>
            <td>Nomor KK</td>
          </thead>
          <tbody>
            <tr v-for="(item, index) in getFiveKK" :key="item.id">
              <td>{{ index + 1 }}</td>
              <td>{{ item.nomor_kk }}</td>
            </tr>
            <tr v-if="getFiveKK.length === 0">
              <td colspan="2" class="noData">Tidak ada data</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="table-card">
      <div class="table-header">
        <h1 class="table-title">Data User</h1>
        <p class="table-desc">
          <span class="red">*</span> Menampilkan 5 data teratas
        </p>
      </div>
      <div class="table-content">
        <table>
          <thead>
            <td>No</td>
            <td>Email</td>
          </thead>
          <tbody>
            <tr v-for="(item, index) in getFiveUser" :key="item.id">
              <td>{{ index + 1 }}</td>
              <td>{{ item.email }}</td>
            </tr>
            <tr v-if="getFiveUser.length === 0">
              <td colspan="2" class="noData">Tidak ada data</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import service from "../services/service.js";
export default {
  name: "HomeTable",
  data() {
    return {
      fiveUser: [],
      fiveKK: [],
    };
  },
  methods: {
    getFiveUserFunc() {
      service
        .getFiveUser()
        .then((response) => {
          this.fiveUser = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },

    getFiveKKFunc() {
      service
        .getFiveKK()
        .then((response) => {
          this.fiveKK = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
  computed: {
    getFiveUser() {
      this.getFiveUserFunc();
      return this.fiveUser;
    },
    getFiveKK() {
      this.getFiveKKFunc();
      return this.fiveKK;
    },
  },
};
</script>

<style lang="scss" scoped>
.table-container {
  display: grid;
  grid-template: 1fr/ 1fr 1fr;
  column-gap: 2rem;

  .table-card {
    background-color: white;
    border-radius: 1rem;
    padding: 1rem 2rem;

    .table-header {
      margin-bottom: 1.5rem;
      .table-title {
        font-weight: 600;
      }
      .table-desc {
        opacity: 0.7;
      }
    }
  }
  table {
    border-collapse: collapse;
    width: 100%;

    thead {
      padding-top: 12px;
      padding-bottom: 12px;
      text-align: left;
      // background-color: ;
      color: black;
      border-bottom: 1px solid #ddd;
    }

    td,
    th {
      padding: 1rem;

      &.noData {
        text-align: center;
      }
    }
    tr:nth-child(odd) {
      background-color: #f2f2f2;
    }

    tr:hover {
      background-color: #ddd;
    }
  }

  .red {
    color: red;
  }
}
</style>