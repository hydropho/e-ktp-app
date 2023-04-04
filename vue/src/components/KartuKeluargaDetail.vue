<template>
  <div class="detail-container">
    <div class="detail-header">
      <div class="top">
        <h1 class="detail-title">Data Kartu Keluarga</h1>
        <div class="top-left">
          <button class="btn btn-edit" v-if="!editMode" @click="enterEditMode">
            <span class="material-symbols-outlined"> edit </span>
            Edit
          </button>
          <button
            class="btn btn-lihat"
            v-if="!editMode"
            @click="enterDetailAnggota"
          >
            <span class="material-symbols-outlined"> group </span>
            Lihat Anggota Keluarga
          </button>
          <button class="btn btn-close" v-if="editMode" @click="closeEditMode">
            <span class="material-symbols-outlined"> close </span>
            Cancel
          </button>
          <button class="btn btn-save" v-if="editMode" @click="inputData">
            <span class="material-symbols-outlined"> done </span>
            Save
          </button>
        </div>
      </div>
      <div class="bot">
        <button class="btn-back" @click="showTable" v-if="!editMode">
          <span class="material-symbols-outlined"> arrow_back </span>Back
        </button>
      </div>
    </div>
    <div class="detail-body">
      <!-- <div class="form-header">
        <h1>Data</h1>
      </div> -->
      <form action="">
        <div class="form-left">
          <div class="form-input">
            <label for="">Nomor KK :</label>
            <input
              type="number"
              :disabled="!editMode"
              v-model="kartuKeluarga.nomor_kk"
            />
            <span v-if="noKKValid" class="validation-message"
              >No KK sudah digunakan!</span
            >
          </div>
          <div class="form-input text-area">
            <label for="">Alamat :</label>
            <textarea
              :disabled="!editMode"
              v-model="kartuKeluarga.alamat"
            ></textarea>
          </div>
          <div class="form-input">
            <label for="">RT :</label>
            <input
              type="number"
              :disabled="!editMode"
              v-model="kartuKeluarga.rt"
            />
          </div>
          <div class="form-input">
            <label for="">RW :</label>
            <input
              type="number"
              :disabled="!editMode"
              v-model="kartuKeluarga.rw"
            />
          </div>
        </div>
        <div class="form-right">
          <div class="form-input">
            <label for="">Desa / Kelurahan :</label>
            <input
              type="text"
              :disabled="!editMode"
              v-model="kartuKeluarga.desa_kelurahan"
            />
          </div>
          <div class="form-input">
            <label for="">Kecamatan :</label>
            <input
              type="text"
              :disabled="!editMode"
              v-model="kartuKeluarga.kecamatan"
            />
          </div>
          <div class="form-input">
            <label for="">Kabupaten / Kota :</label>
            <input
              type="text"
              :disabled="!editMode"
              v-model="kartuKeluarga.kabupaten_kota"
            />
          </div>
          <div class="form-input">
            <label for="">Provinsi :</label>
            <input
              type="text"
              :disabled="!editMode"
              v-model="kartuKeluarga.provinsi"
            />
          </div>
          <div class="form-input">
            <label for="">Kode Pos :</label>
            <input
              type="number"
              :disabled="!editMode"
              v-model="kartuKeluarga.kode_pos"
            />
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import service from "../services/service";
export default {
  data() {
    return {
      editMode: false,
      kartuKeluarga: {
        nomor_kk: null,
        alamat: "",
        rt: null,
        rw: null,
        desa_kelurahan: "",
        kecamatan: "",
        kabupaten_kota: "",
        provinsi: "",
        kode_pos: null,
      },
      noKKValid: false,
    };
  },
  methods: {
    showTable() {
      this.$emit("showTable");
    },
    enterEditMode() {
      this.editMode = !this.editMode;
    },
    enterDetailAnggota() {
      this.$emit("showAnggotaKeluarga", this.sendId);
    },
    closeEditMode() {
      this.getSelectedKK();
      this.showMsg("cancelled");
      this.editMode = !this.editMode;
      this.noKKValid = false;
    },
    showMsg(status) {
      if (status === "success") {
        const data = {
          status: "success",
          header: "Berhasil !",
          body: "Kartu keluarga berhasil diubah!",
          icon: "done",
        };
        this.$emit("sendToastMsg", data);
      } else if (status === "cancelled") {
        const data = {
          status: "danger",
          header: "Cancelled !",
          body: "Edit dibatalkan!",
          icon: "close",
        };
        this.$emit("sendToastMsg", data);
      } else if (status === "gagal") {
        const data = {
          status: "danger",
          header: "Gagal !",
          body: "Terjadi kesalahan!",
          icon: "close",
        };
        this.$emit("sendToastMsg", data);
      }
      var x = document.getElementById("snackbar");
      x.className = "show";
      setTimeout(function () {
        x.className = x.className.replace("show", "");
      }, 3000);
    },
    inputData() {
      this.noKKValid = false;
      service
        .updateKK(this.sendId, this.kartuKeluarga)
        .then((response) => {
          if (response.status === 200) {
            this.showMsg("success");
            this.editMode = !this.editMode;
          }
        })
        .catch((e) => {
          if (e.response.data.trace.includes("Duplicate entry")) {
            this.noKKValid = true;
            this.showMsg("gagal");
          }
        });
    },
    getSelectedKK() {
      let id = this.sendId;
      service
        .getSelectedKK(id)
        .then((response) => {
          this.kartuKeluarga = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
  props: ["sendId"],
  mounted() {
    this.getSelectedKK();
  },
};
</script>

<style lang="scss" scoped>
.detail-container {
  // width: calc(100% - 5rem);
  padding: 2rem;
  background-color: white;
  border-radius: 1rem;

  .detail-header {
    display: flex;
    flex-direction: column;

    .top {
      width: 100%;
      display: flex;
      justify-content: space-between;
      align-items: center;
      border-bottom: 1px solid #ddd;
      padding-bottom: 1rem;

      .top-left {
        display: flex;

        button {
          margin-left: 0.5rem;
        }
      }

      .btn {
        display: flex;
        align-items: center;
        background: none;
        padding: 0.5rem 1rem;
        border: none;
        border-radius: 0.7rem;
        color: white;
        cursor: pointer;

        &.btn-save {
          background-color: hsl(241.21, 40.41%, 48.04%);

          &:hover {
            background-color: hsl(241.21, 40.41%, 58.04%);
          }
        }

        &.btn-lihat {
          background-color: hsl(157.95, 95.4%, 34.12%);

          &:hover {
            background-color: hsl(157.95, 95.4%, 44.12%);
          }
        }

        &.btn-edit {
          background-color: hsl(37.06, 93.15%, 71.37%);

          &:hover {
            background-color: hsl(37.06, 93.15%, 81.37%);
          }
        }

        &.btn-close {
          background-color: hsl(6.18, 93.15%, 71.37%);

          &:hover {
            background-color: hsl(6.18, 93.15%, 81.37%);
          }
        }

        span {
          margin-right: 0.5rem;
        }
      }
    }

    .bot {
      width: 100%;
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
    }
  }

  .detail-body {
    form {
      display: grid;
      grid-template: 1fr / 1fr 1fr;
      column-gap: 1rem;
      // align-items: ;
      // width: 100%;

      .form-left {
        height: 100%;
        // width: 100%;
      }

      .form-input {
        width: 100%;
        // height: 100%;
        margin-bottom: 1rem;

        .validation-message {
          color: red;
          opacity: 0.7;
          font-size: 0.8rem;
        }

        input,
        textarea {
          margin-top: 0.5rem;
          padding: 0.5rem 1rem;
          width: 100%;
          border-radius: 0.5rem;
          border: 1px solid #ddd;
        }

        textarea {
          height: 7.4rem;
        }
      }
    }
  }
}
</style>