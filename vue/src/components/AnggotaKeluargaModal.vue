<template>
  <div id="myModal" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
      <div class="modal-header">
        <h2>{{ actionForm }} Anggota Keluarga</h2>
        <span class="close" @click="closeModal">&times;</span>
      </div>
      <div class="modal-body">
        <form action="" @submit.prevent="inputAnggotaKeluarga">
          <div class="form-input">
            <label for="">NIK <span>*</span></label>
            <input type="number" v-model="anggotaKeluarga.nik" />
            <span v-if="nikValidation" class="validation-message"
              >Nik sudah ada!</span
            >
            <span v-if="error.nik" class="validation-message"
              >NIK harus diisi!</span
            >
          </div>
          <div class="form-input">
            <label for="">Nama <span>*</span></label>
            <input type="text" v-model="anggotaKeluarga.nama" />
            <span v-if="error.nama" class="validation-message"
              >Nama harus diisi!</span
            >
          </div>
          <div class="form-input">
            <label for="">Jenis Kelamin <span>*</span></label>
            <select v-model="anggotaKeluarga.jenis_kelamin">
              <option value="" selected disabled>Pilih jenis kelamin.</option>
              <option>Laki - Laki</option>
              <option>Perempuan</option>
            </select>
            <span v-if="error.jenis_kelamin" class="validation-message"
              >Jenis kelamin harus diisi!</span
            >
          </div>
          <div class="form-input">
            <label for="">Tempat Lahir <span>*</span></label>
            <input type="text" v-model="anggotaKeluarga.tempat_lahir" />
            <span v-if="error.tempat_lahir" class="validation-message"
              >Tempat lahir harus diisi!</span
            >
          </div>
          <div class="form-input">
            <label for="">Tanggal Lahir <span>*</span></label>
            <input type="date" v-model="anggotaKeluarga.tanggal_lahir" />
            <span v-if="error.tanggal_lahir" class="validation-message"
              >Tanggal lahir harus diisi!</span
            >
          </div>
          <div class="form-input">
            <label for="">Agama <span>*</span></label>
            <select v-model="anggotaKeluarga.agama">
              <option value="" selected disabled>Pilih agama.</option>
              <option>Islam</option>
              <option>Kristen</option>
              <option>Katholik</option>
              <option>Hindu</option>
              <option>Budha</option>
              <option>Konghucu</option>
            </select>
            <span v-if="error.agama" class="validation-message"
              >Agama harus diisi!</span
            >
          </div>
          <div class="form-input">
            <label for="">Pendidikan <span>*</span></label>
            <input type="text" v-model="anggotaKeluarga.pendidikan" />
            <span v-if="error.pendidikan" class="validation-message"
              >Pendidikan harus diisi!</span
            >
          </div>
          <div class="form-input">
            <label for="">Kepala keluarga <span>*</span></label>
            <select v-model="anggotaKeluarga.kepala_keluarga">
              <option value="" selected disabled>Pilih kepala keluarga</option>
              <option>Ya</option>
              <option>Tidak</option>
            </select>
            <span v-if="error.kepala_keluarga" class="validation-message"
              >Kepala keluarga harus diisi!</span
            >
          </div>
          <div class="btn-input">
            <button
              type="reset"
              class="btn-reset"
              v-if="actionForm != 'Detail'"
            >
              Reset
            </button>
            <button type="submit" class="btn-submit">Submit</button>
          </div>
        </form>
      </div>
      <!-- <div class="modal-footer">
            <h3>Modal Footer</h3>
        </div> -->
    </div>
  </div>
</template>

<script>
import service from "../services/service.js";
export default {
  data() {
    return {
      anggotaKeluarga: {
        nik: null,
        nama: "",
        jenis_kelamin: "",
        tempat_lahir: "",
        tanggal_lahir: null,
        agama: "",
        pendidikan: "",
        kepala_keluarga: "",
        id_kk: null,
      },
      nikValidation: false,
      error: {},
      actionForm: "",
    };
  },
  methods: {
    closeModal() {
      this.$emit("resetAction");
      document.getElementById("myModal").style.display = "none";
    },
    showMsg(status) {
      if (status === "successInsert") {
        const data = {
          status: "success",
          header: "Berhasil !",
          body: "Anggota keluarga ditambahkan!",
          icon: "done",
        };
        this.$emit("sendToastMsg", data);
      } else if (status === "gagalInsert") {
        const data = {
          status: "danger",
          header: "Gagal !",
          body: "Terdapat kesalahan!",
          icon: "close",
        };
        this.$emit("sendToastMsg", data);
      } else if (status === "successEdit") {
        const data = {
          status: "success",
          header: "Berhasil !",
          body: "Anggota keluarga diedit!",
          icon: "done",
        };
        this.$emit("sendToastMsg", data);
      } else if (status === "gagalEdit") {
        const data = {
          status: "danger",
          header: "Gagal !",
          body: "Terdapat kesalahan!",
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
    inputAnggotaKeluarga() {
      this.nikValidation = false;
      this.anggotaKeluarga.id_kk = this.sendId;
      this.error = {};
      let data = this.anggotaKeluarga;
      if (this.actionForm === "Tambah") {
        for (const property in data) {
          if (data[property] === null || data[property] === "") {
            this.error[property] = true;
          }
        }
        if (Object.keys(this.error).length === 0) {
          service
            .insertAnggotaKeluarga(this.anggotaKeluarga)
            .then((response) => {
              if (response.status === 200) {
                this.closeModal();
                this.showMsg("successInsert");
                this.anggotaKeluarga = {};
              }
            })
            .catch((e) => {
              let response = e.response.data.trace.includes("Duplicate entry");
              if (response === true) {
                this.nikValidation = true;
              }
              this.showMsg("gagalInsert");
              this.anggotaKeluarga = data;
            });
        }
      } else if (this.actionForm === "Detail") {
        service
          .updateAnggotaKeluarga(this.sendAnggotaId, data)
          .then((response) => {
            if (response.status === 200) {
              this.closeModal();
              this.showMsg("successEdit");
              this.anggotaKeluarga = {};
            }
          })
          .catch((e) => {
            let response = e.response.data.trace.includes("Duplicate entry");
            if (response === true) {
              this.nikValidation = true;
            }
            this.showMsg("gagalEdit");
            this.anggotaKeluarga = data;
          });
      }
    },
  },
  props: ["action", "sendId", "sendAnggotaId"],
  watch: {
    action(newValue) {
      if (newValue === "Tambah") {
        this.anggotaKeluarga = {
          nik: null,
          nama: "",
          jenis_kelamin: "",
          tempat_lahir: "",
          tanggal_lahir: null,
          agama: "",
          pendidikan: "",
          kepala_keluarga: "",
          id_kk: null,
        };
        this.actionForm = newValue;
      } else if (newValue === "Detail") {
        service
          .getSelectedAnggotaKeluarga(this.sendAnggotaId)
          .then((response) => {
            this.anggotaKeluarga = response.data;
            this.actionForm = newValue;
            this.$emit("resetAction");
          })
          .catch((e) => {
            console.log(e);
          });
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 1rem; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0, 0, 0); /* Fallback color */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
  position: relative;
  border-radius: 1rem;
  background-color: white;
  margin: auto;
  padding: 2rem;
  width: 50%;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  -webkit-animation-name: animatetop;
  -webkit-animation-duration: 0.4s;
  animation-name: animatetop;
  animation-duration: 0.4s;
}

/* Add Animation */
@-webkit-keyframes animatetop {
  from {
    top: -300px;
    opacity: 0;
  }
  to {
    top: 0;
    opacity: 1;
  }
}

@keyframes animatetop {
  from {
    top: -300px;
    opacity: 0;
  }
  to {
    top: 0;
    opacity: 1;
  }
}

.modal-header {
  padding-bottom: 0.5rem;
  margin-bottom: 1rem;
  border-bottom: 1px solid #ddd;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

/* The Close Button */
.close {
  color: black;
  font-size: 2rem;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: salmon;
  text-decoration: none;
  cursor: pointer;
}

.form-input {
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 1rem;
  color: hsl(221.74, 9.62%, 46.86%);

  input,
  select {
    width: 100%;
    padding: 0.7rem 1rem;
    border-radius: 0.7rem;
    border: 1px solid #ddd;
    margin-top: 0.5rem;

    /* Chrome, Safari, Edge, Opera */
    &::-webkit-outer-spin-button,
    &::-webkit-inner-spin-button {
      -webkit-appearance: none;
      margin: 0;
    }
  }

  .validation-message {
    color: red;
    opacity: 0.7;
    font-size: 0.8rem;
  }

  label {
    span {
      color: red;
      opacity: 0.7;
    }
  }
}

.btn-input {
  display: flex;
  justify-content: flex-end;

  button {
    background: none;
    border: none;
    padding: 0.6rem 1rem;
    border-radius: 0.6rem;
    color: white;
    cursor: pointer;

    &.btn-reset {
      background-color: salmon;
      margin-right: 0.5rem;
    }

    &.btn-submit {
      background-color: hsl(241.21, 40.41%, 48.04%);
    }
  }
}
</style>