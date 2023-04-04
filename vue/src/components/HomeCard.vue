<template>
  <div class="card-container">
    <div class="card-link">
      <div class="card">
        <div class="card-desc">
          <p class="card-title">Penduduk</p>
          <p class="card-num">{{ penduduk.length }}</p>
        </div>
        <div class="card-logo">
          <span class="material-symbols-outlined"> group </span>
        </div>
      </div>
    </div>
    <router-link to="/kartu-keluarga" class="card-link">
      <div class="card">
        <div class="card-desc">
          <p class="card-title">Kartu Keluarga</p>
          <p class="card-num">{{ kartuKeluarga.length }}</p>
        </div>
        <div class="card-logo">
          <span class="material-symbols-outlined"> description </span>
        </div>
      </div>
    </router-link>
    <router-link to="/users" class="card-link">
      <div class="card">
        <div class="card-desc">
          <p class="card-title">Users</p>
          <p class="card-num">{{ users.length }}</p>
        </div>
        <div class="card-logo">
          <span class="material-symbols-outlined"> person </span>
        </div>
      </div>
    </router-link>
  </div>
</template>

<script>
import service from "../services/service";

export default {
  name: "HomeCard",
  data() {
    return {
      penduduk: [],
      kartuKeluarga: [],
      users: [],

      fiveUser: [],
      fiveKK: [],
    };
  },
  methods: {
    getAllAnggotaKeluarga() {
      service
        .getAllAK()
        .then((response) => {
          this.penduduk = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },

    getAllKartuKeluarga() {
      service
        .getAllKK()
        .then((response) => {
          this.kartuKeluarga = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },

    getAllUser() {
      service
        .getAllUser()
        .then((response) => {
          this.users = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
  mounted() {
    this.getAllAnggotaKeluarga();
    this.getAllKartuKeluarga();
    this.getAllUser();
  },
};
</script>

<style lang="scss" scoped>
.card-container {
  display: grid;
  grid-template: 1fr / repeat(3, 1fr);
  column-gap: 1rem;

  .card-link {
    text-decoration: none;
    color: white;
    padding: 1rem 1.5rem;
    border-radius: 1rem;
    display: flex;
    align-items: center;

    &:nth-child(1) {
      background-color: hsl(240.53, 71.97%, 69.22%);
      &:hover {
        background-color: hsl(240.53, 71.97%, 79.22%);
      }
    }

    &:nth-child(2) {
      background-color: hsl(241.21, 40.41%, 48.04%);
      &:hover {
        background-color: hsl(241.21, 40.41%, 58.04%);
      }
    }

    &:nth-child(3) {
      background-color: hsl(223.2, 92.59%, 73.53%);
      &:hover {
        background-color: hsl(223.2, 92.59%, 83.53%);
      }
    }

    .card {
      width: 100%;
      display: flex;
      justify-content: space-between;
      align-items: center;

      .card-desc {
        .card-title {
          font-size: 1rem;
        }

        .card-num {
          font-size: 2rem;
          font-weight: 600;
        }
      }

      .card-logo {
        span {
          font-size: 3.5rem;
        }
      }
    }
  }
}
</style>