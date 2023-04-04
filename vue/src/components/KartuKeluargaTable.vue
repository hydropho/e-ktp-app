<template>
  <div class="table-container">
    <Modal :action="action" @sendToastMsg="sendToastMsg" />
    <div class="table-header">
      <div class="top">
        <h1 class="table-title">Data Kartu Keluarga</h1>
        <button class="btn-add" id="btn-add" @click="showModal">
          <span class="material-symbols-outlined"> add </span>
          Add
        </button>
      </div>
      <div class="bot">
        <div class="table-search">
          <label for="">Search : </label>
          <input type="text" class="search-bar" v-model="search" />
        </div>
      </div>
    </div>
    <table>
      <thead>
        <td>No</td>
        <td>Nomor KK</td>
        <td>Alamat</td>
        <td>Kota</td>
        <td>Kode Pos</td>
        <td>Action</td>
      </thead>
      <tbody>
        <tr v-for="(item, index) in getAllKKFunc" :key="item.id">
          <td>{{ index + 1 }}</td>
          <td>{{ item.nomor_kk }}</td>
          <td>
            {{
              item.alamat + ", " + item.desa_kelurahan + ", " + item.kecamatan
            }}
          </td>
          <td>{{ item.kabupaten_kota }}</td>
          <td>{{ item.kode_pos }}</td>
          <td>
            <button class="btn-detail" @click="showDetail(item.id)">
              Detail
            </button>
            <button class="btn-delete" @click="deleteKK(item.id)">
              Delete
            </button>
          </td>
        </tr>
        <tr v-if="getAllKKFunc.length === 0">
          <td colspan="6" class="noData">Tidak ada data</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import Modal from "./KartuKeluargaModal.vue";
import service from "../services/service.js";
export default {
  components: {
    Modal,
  },
  data() {
    return {
      display: "none",
      action: "",
      kartuKeluarga: [],
      search: "",
      temp: [],
    };
  },
  methods: {
    showModal() {
      this.action = "Tambah";
      document.getElementById("myModal").style.display = "block";
    },
    showDetail(id) {
      // this.action = "Detail";
      this.$emit("showDetail", id);
    },
    sendToastMsg(data) {
      this.$emit("sendToastMsg", data);
    },
    deleteKK(id) {
      const swalWithBootstrapButtons = this.$swal.mixin({
        customClass: {
          confirmButton: "btn btn-confirm",
          cancelButton: "btn btn-cancel",
        },
        buttonsStyling: true,
      });

      swalWithBootstrapButtons
        .fire({
          title: "Apakah anda yakin ingin menghapus kartu keluarga ini?",
          text: "Data tidak bisa dikembalikan!",
          icon: "warning",
          showCancelButton: true,
          confirmButtonText: "Hapus!",
          cancelButtonText: "Cancel!",
          reverseButtons: true,
        })
        .then((result) => {
          if (result.isConfirmed) {
            service.deleteKK(id).then((response) => {
              if (response.status === 200) {
                service.deleteAllAnggotaKeluarga(id);
              }
            });
            swalWithBootstrapButtons.fire(
              "Terhapus!",
              "Kartu keluarga ini telah terhapus!",
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
    getAllKK() {
      service
        .getAllKK()
        .then((response) => {
          this.kartuKeluarga = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    filteredData() {
      this.temp = [];
      this.kartuKeluarga.filter((data) => {
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
    getAllKKFunc() {
      this.getAllKK();
      if (this.search === "") {
        return this.kartuKeluarga;
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
};
window.onclick = function (event) {
  if (event.target == document.getElementById("myModal")) {
    document.getElementById("myModal").style.display = "none";
  }
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
      flex-direction: row-reverse;
      padding: 1rem 0;
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