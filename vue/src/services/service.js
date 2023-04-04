import http from '../http-common.js'

class service {
    // ========== USER ===========
    getAllUser() {
        return http.get("/user/getAll");
    }

    getSelectedUser(id) {
        return http.get(`/user/getUser/${id}`);
    }

    updateUser(id, data) {
        return http.put(`/user/update/${id}`, data);
    }

    deleteUser(id) {
        return http.delete(`/user/delete/${id}`);
    }

    getFiveUser() {
        return http.get("/user/getFiveUser");
    }

    login(data) {
        return http.post("/user/login", data);
    }

    register(data) {
        return http.post("/user/register", data);
    }

    // ========== KartuKeluarga ===========
    getAllKK() {
        return http.get("/kartu-keluarga/getAll");
    }

    insertKK(data) {
        return http.post("/kartu-keluarga/insert", data);
    }

    getSelectedKK(id) {
        return http.get(`/kartu-keluarga/getKK/${id}`);
    }

    updateKK(id, data) {
        return http.put(`/kartu-keluarga/update/${id}`, data);
    }

    deleteKK(id) {
        return http.delete(`/kartu-keluarga/delete/${id}`);
    }

    getFiveKK() {
        return http.get("/kartu-keluarga/getFiveKK");
    }

    // ========== AnggotaKeluarga ===========
    getAllAK() {
        return http.get("/anggota-keluarga/getAll");
    }

    getAllAnggotaKeluarga(id) {
        return http.get(`/anggota-keluarga/getAllById/${id}`);
    }

    insertAnggotaKeluarga(data) {
        return http.post("/anggota-keluarga/insert", data);
    }

    getSelectedAnggotaKeluarga(id) {
        return http.get(`/anggota-keluarga/getAnggotaKeluarga/${id}`);
    }

    updateAnggotaKeluarga(id, data) {
        return http.put(`/anggota-keluarga/update/${id}`, data);
    }

    deleteAnggotaKeluarga(id) {
        return http.delete(`/anggota-keluarga/delete/${id}`);
    }

    deleteAllAnggotaKeluarga(id) {
        return http.delete(`/anggota-keluarga/deleteAll/${id}`);
    }
}

export default new service();