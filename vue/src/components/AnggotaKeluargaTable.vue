<template>
  <div>
    <Modal
      :action="action"
      @resetAction="resetAction"
      :sendId="sendId"
      @sendToastMsg="sendToastMsg"
      :sendAnggotaId="sendAnggotaId"
    />
    <div class="table-container">
      <div class="table-header">
        <div class="top">
          <h1 class="table-title">Data Anggota Keluarga</h1>
          <button class="btn-add" id="btn-add" @click="showModal">
            <span class="material-symbols-outlined"> add </span>
            Add
          </button>
        </div>
        <div class="bot">
          <button class="btn-back" @click="showDetailComp">
            <span class="material-symbols-outlined"> arrow_back </span>Back
          </button>
          <div class="table-search">
            <label for="">Search : </label>
            <input type="text" class="search-bar" v-model="search" />
          </div>
        </div>
      </div>
      <table>
        <thead>
          <td>No</td>
          <td>NIK</td>
          <td>Nama</td>
          <td>Jenis Kelamin</td>
          <td>Kepala Keluarga</td>
          <td>Action</td>
        </thead>
        <tbody>
          <tr v-for="(item, index) in getAllAnggotaKeluarga" :key="item.id">
            <td>{{ index + 1 }}</td>
            <td>{{ item.nik }}</td>
            <td>{{ item.nama }}</td>
            <td>{{ item.jenis_kelamin }}</td>
            <td>{{ item.kepala_keluarga }}</td>
            <td>
              <button class="btn-detail" @click="showDetail(item.id)">
                Detail
              </button>
              <button
                class="btn-delete"
                @click="deleteAnggotaKeluarga(item.id)"
              >
                Delete
              </button>
            </td>
          </tr>
          <tr v-if="anggotaKeluarga.length === 0">
            <td colspan="6" class="noData">Tidak ada data</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import Modal from "./AnggotaKeluargaModal.vue";
import service from "../services/service.js";
export default {
  components: {
    Modal,
  },
  data() {
    return {
      anggotaKeluarga: [],
      action: "",
      sendAnggotaId: null,
      temp: [],
      search: "",
    };
  },
  methods: {
    showDetailComp() {
      this.$emit("showDetail");
    },
    showModal() {
      this.action = "Tambah";
      document.getElementById("myModal").style.display = "block";
    },
    showDetail(id) {
      this.sendAnggotaId = id;
      this.action = "Detail";
      document.getElementById("myModal").style.display = "block";
    },
    resetAction() {
      this.action = "";
    },
    sendToastMsg(data) {
      this.$emit("sendToastMsg", data);
    },
    getAnggotaKeluarga() {
      service
        .getAllAnggotaKeluarga(this.sendId)
        .then((response) => {
          this.anggotaKeluarga = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    deleteAnggotaKeluarga(id) {
      const swalWithBootstrapButtons = this.$swal.mixin({
        customClass: {
          confirmButton: "btn btn-confirm",
          cancelButton: "btn btn-cancel",
        },
        buttonsStyling: true,
      });

      swalWithBootstrapButtons
        .fire({
          title: "Apakah anda yakin ingin menghapus anggota keluarga ini?",
          text: "Data tidak bisa dikembalikan!",
          icon: "warning",
          showCancelButton: true,
          confirmButtonText: "Hapus!",
          cancelButtonText: "Cancel!",
          reverseButtons: true,
        })
        .then((result) => {
          if (result.isConfirmed) {
            service.deleteAnggotaKeluarga(id);
            swalWithBootstrapButtons.fire(
              "Terhapus!",
              "Anggota keluarga ini telah terhapus!",
              "success"
            );
          } else if (
            /* Read more about handling dismissals below */
            result.dismiss === this.$swal.DismissReason.cancel
          ) {
            swalWithBootstrapButtons.fire(
              "Gagal menghapus!",
              "Proses dibatalkan!",
              "error"
            );
          }
        });
    },
    filteredData() {
      this.temp = [];
      this.anggotaKeluarga.filter((data) => {
        Object.values(data).forEach((item) => {
          if (String(item).toLowerCase().includes(this.search.toLowerCase())) {
            if (this.temp.length === 0) {
              this.temp.push(data);
            } else if (
              this.temp.length !== 0 &&
              this.temp[this.temp.length - 1].id !== data.id
            ) {
              this.temp.push(data);
            }
          }
        });
      });
    },
  },
  computed: {
    getAllAnggotaKeluarga() {
      this.getAnggotaKeluarga();
      if (this.search === "") {
        return this.anggotaKeluarga;
      } else {
        return this.temp;
      }
    },
  },
  watch: {
    search() {
      this.filteredData();
    },
  },
  props: ["sendId"],
  mounted() {
    this.getAnggotaKeluarga();
  },
};
</script>

<style lang="scss" scoped>
.table-container {
  // width: calc(100% - 5rem);
  padding: 2rem;
  background-color: white;
  border-radius: 1rem;

  .table-header {
    display: flex;
    flex-direction: column;
    // justify-content: space-between;

    .top {
      display: flex;
      justify-content: space-between;
      align-items: center;
      border-bottom: 1px solid #ddd;
      padding-bottom: 1rem;

      .btn-add {
        display: flex;
        align-items: center;
        background: none;
        background-color: hsl(241.21, 40.41%, 48.04%);
        padding: 0.5rem 1rem;
        border: none;
        border-radius: 0.7rem;
        color: white;
        cursor: pointer;

        &:hover {
          background-color: hsl(241.21, 40.41%, 58.04%);
        }

        span {
          margin-right: 0.5rem;
        }
      }
    }

    .bot {
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      padding: 1rem 0;

      .btn-back {
        display: flex;
        align-items: center;
        background: none;
        border: none;
        background-color: hsl(6.18, 93.15%, 71.37%);
        padding: 0.5rem 1rem;
        color: white;
        border-radius: 0.7rem;

        .material-symbols-outlined {
          margin-right: 0.5rem;
        }

        &:hover {
          cursor: pointer;
          background-color: hsl(6.18, 93.15%, 81.37%);
        }
      }

      .table-search {
        label {
          margin-right: 0.5rem;
        }
        .search-bar {
          background: none;
          padding: 0.3rem 0.5rem;
          border-radius: 0.5rem;
          border: 1px solid black;
          width: 15rem;
        }
      }
    }
  }

  table {
    border-collapse: collapse;
    width: 100%;

    thead {
      text-align: left;
      //   background-color: salmon;
      color: black;
      border-bottom: 1px solid #ddd;
    }

    td,
    th {
      padding: 1rem;

      button {
        background: none;
        border: none;
        padding: 0.6rem 1rem;
        border-radius: 0.6rem;
        color: white;
        cursor: pointer;

        &.btn-delete {
          background-color: hsl(6.18, 93.15%, 71.37%);
          margin-left: 0.5rem;

          &:hover {
            background-color: hsl(6.18, 93.15%, 81.37%);
          }
        }

        &.btn-detail {
          background-color: hsl(241.21, 40.41%, 48.04%);

          &:hover {
            background-color: hsl(241.21, 40.41%, 58.04%);
          }
        }
      }

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
}
</style>