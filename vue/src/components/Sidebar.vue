<template>
  <aside>
    <div class="sidebar-header">
      <h1 class="title">E-KTP</h1>
    </div>
    <div class="sidebar-body">
      <ul>
        <router-link class="link-item" to="/" exact>
          <span class="material-symbols-outlined"> home </span>
          <li>Home</li>
        </router-link>
        <router-link class="link-item" to="/kartu-keluarga">
          <span class="material-symbols-outlined"> description </span>
          <li>Kartu Keluarga</li>
        </router-link>
        <!-- <router-link class="link-item" to="/penduduk">
          <span class="material-symbols-outlined"> group </span>
          <li>Penduduk</li>
        </router-link> -->
        <router-link class="link-item" to="/users">
          <span class="material-symbols-outlined"> person </span>
          <li>Users</li>
        </router-link>
      </ul>
    </div>
    <div class="sidebar-footer">
      <router-link to="/auth" class="link-item">
        <span class="material-symbols-outlined"> logout </span>
        <p>Logout</p>
      </router-link>
    </div>
  </aside>
</template>

<script>
export default {
  name: "SidebarComp",
};
</script>

<style lang="scss" scoped>
aside {
  position: fixed;
  //   margin: 1rem 0 1rem 1rem;
  // min-height: (100%;
  min-height: calc(100vh - 2rem);
  // min-height: 100vh;
  // width: calc(4rem + 32px);
  border-radius: 1rem;
  background-color: white;
  padding: 2rem;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  transition: 0.3s ease;

  @media (max-width: 768px) {
    width: calc(4rem + 32px);
    position: fixed;
    z-index: 99;
  }

  .material-symbols-outlined {
    font-size: 1.5rem;
    margin-right: 1rem;

    @media (max-width: 768px) {
      font-size: 2rem;
      margin-right: 2rem;
    }
  }

  .sidebar-header {
    margin-bottom: 1rem;

    .title {
      color: hsl(241.21, 40.41%, 48.04%);
      @media (min-width: 768px) {
        &::after {
          content: " App";
        }
      }
    }
  }

  .sidebar-body {
    ul {
      list-style: none;
    }
  }

  .link-item {
    text-decoration: none;
    color: hsl(221.74, 9.62%, 46.86%);
    display: flex;
    align-items: center;
    font-size: 1rem;
    padding: 0.5rem 1rem;
    border-radius: 0.7rem;
    margin-bottom: 0.5rem;

    li,
    p {
      padding: 0.5rem;
      width: 100%;
    }

    &:hover {
      color: white;
      background-color: hsl(241.21, 40.41%, 48.04%);
      //   background-color: white;
      width: 100%;
    }

    @media (max-width: 768px) {
      padding: 1rem 0;

      li,
      p {
        display: none;
      }
    }
  }
  .router-link-active,
  .router-link-exact-active {
    color: white;
    background-color: hsl(241.21, 40.41%, 48.04%);
  }

  .sidebar-footer {
    margin-top: auto;
  }
}
</style>