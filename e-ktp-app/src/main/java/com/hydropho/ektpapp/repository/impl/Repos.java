package com.hydropho.springboot.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hydropho.springboot.model.AnggotaKeluarga;
import com.hydropho.springboot.model.KartuKeluarga;
import com.hydropho.springboot.model.User;
import com.hydropho.springboot.repository.IRepository;

@Repository
public class Repos implements IRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // ================ USER =================
    @Override
    public List<User> getAllUser() {
        // TODO Auto-generated method stub
        String query = "SELECT * FROM tb_user";

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User insertUser(User user) {
        // TODO Auto-generated method stub
        String query = "INSERT INTO tb_user(email, nama, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, new Object[] { user.getEmail(), user.getNama(), user.getPassword() });

        return user;
    }

    @Override
    public User getSelectedUser(int id) {
        // TODO Auto-generated method stub
        String query = "SELECT * FROM tb_user WHERE id = ?";
        var result = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(User.class), id);

        return result;
    }

    @Override
    public User updateUser(int id, User user) {
        // TODO Auto-generated method stub
        String query = "UPDATE tb_user SET email = ?, nama = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(query, new Object[] { user.getEmail(), user.getNama(), user.getPassword(), id });

        return user;
    }

    @Override
    public User deleteUser(int id) {
        // TODO Auto-generated method stub
        String query = "SELECT * FROM tb_user WHERE id = ?";
        var result = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(User.class), id);

        query = "DELETE FROM tb_user WHERE id = ?";
        jdbcTemplate.update(query, id);

        return result;
    }

    @Override
    public List<User> getFiveUsers() {
        // TODO Auto-generated method stub
        String query = "SELECT * FROM tb_user ORDER BY id DESC LIMIT 5";

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User login(User user) {
        String query = "SELECT * FROM tb_user WHERE email = ? AND password = ?";

        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(User.class),
                new Object[] { user.getEmail(), user.getPassword() });
    }

    @Override
    public User register(User user) {
        String query = "INSERT INTO tb_user(email, nama, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, new Object[] { user.getEmail(), user.getNama(), user.getPassword() });

        return user;
    }

    // ================ KartuKeluarga =================
    @Override
    public List<KartuKeluarga> getAllKK() {
        // TODO Auto-generated method stub
        String query = "SELECT * FROM tb_kartu_keluarga";

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(KartuKeluarga.class));
    }

    @Override
    public KartuKeluarga insertKK(KartuKeluarga kartuKeluarga) {
        // TODO Auto-generated method stub
        String query = "INSERT INTO tb_kartu_keluarga(alamat, desa_kelurahan, kabupaten_kota, kecamatan, kode_pos, nomor_kk, provinsi, rt, rw) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query,
                new Object[] { kartuKeluarga.getAlamat(), kartuKeluarga.getDesa_kelurahan(),
                        kartuKeluarga.getKabupaten_kota(), kartuKeluarga.getKecamatan(), kartuKeluarga.getKode_pos(),
                        kartuKeluarga.getNomor_kk(), kartuKeluarga.getProvinsi(), kartuKeluarga.getRt(),
                        kartuKeluarga.getRw() });

        return kartuKeluarga;
    }

    @Override
    public KartuKeluarga getSelectedKK(int id) {
        // TODO Auto-generated method stub
        String query = "SELECT * FROM tb_kartu_keluarga WHERE id = ?";
        var result = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(KartuKeluarga.class), id);

        return result;
    }

    @Override
    public KartuKeluarga updateKK(int id, KartuKeluarga kartuKeluarga) {
        // TODO Auto-generated method stub

        String query = "UPDATE tb_kartu_keluarga SET alamat = ?, desa_kelurahan = ?, kabupaten_kota = ?, kecamatan = ?, kode_pos = ?, nomor_kk = ?, provinsi = ?, rt = ?, rw = ? WHERE id = ?";
        jdbcTemplate.update(query,
                new Object[] { kartuKeluarga.getAlamat(), kartuKeluarga.getDesa_kelurahan(),
                        kartuKeluarga.getKabupaten_kota(), kartuKeluarga.getKecamatan(), kartuKeluarga.getKode_pos(),
                        kartuKeluarga.getNomor_kk(), kartuKeluarga.getProvinsi(), kartuKeluarga.getRt(),
                        kartuKeluarga.getRw(), id });

        return kartuKeluarga;
    }

    @Override
    public KartuKeluarga deleteKK(int id) {
        // TODO Auto-generated method stub
        String query = "SELECT * FROM tb_kartu_keluarga WHERE id = ?";
        var result = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(KartuKeluarga.class), id);

        query = "DELETE FROM tb_kartu_keluarga WHERE id = ?";
        jdbcTemplate.update(query, id);
        return result;
    }

    @Override
    public List<KartuKeluarga> getFiveKK() {
        // TODO Auto-generated method stub
        String query = "SELECT * FROM tb_kartu_keluarga ORDER BY id DESC LIMIT 5";

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(KartuKeluarga.class));
    }

    // ================ AnggotaKeluarga =================
    @Override
    public List<AnggotaKeluarga> getAllAnggotaKeluarga(int id) {
        // TODO Auto-generated method stub
        String query = "SELECT * FROM tb_anggota_keluarga WHERE id_kk = ?";

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(AnggotaKeluarga.class), id);
    }

    @Override
    public AnggotaKeluarga insertAnggotaKeluarga(AnggotaKeluarga anggotaKeluarga) {
        // TODO Auto-generated method stub
        String query = "INSERT INTO tb_anggota_keluarga(agama, id_kk, jenis_kelamin, kepala_keluarga, nama, nik, pendidikan, tanggal_lahir, tempat_lahir) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query,
                new Object[] { anggotaKeluarga.getAgama(), anggotaKeluarga.getId_kk(),
                        anggotaKeluarga.getJenis_kelamin(), anggotaKeluarga.getKepala_keluarga(),
                        anggotaKeluarga.getNama(), anggotaKeluarga.getNik(), anggotaKeluarga.getPendidikan(),
                        anggotaKeluarga.getTanggal_lahir(), anggotaKeluarga.getTempat_lahir() });

        return anggotaKeluarga;
    }

    @Override
    public AnggotaKeluarga getSelectedAnggotaKeluarga(int id) {
        // TODO Auto-generated method stub
        String query = "SELECT * FROM tb_anggota_keluarga WHERE id = ?";
        var result = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(AnggotaKeluarga.class), id);

        return result;
    }

    @Override
    public AnggotaKeluarga updateAnggotaKeluarga(int id, AnggotaKeluarga anggotaKeluarga) {
        // TODO Auto-generated method stub
        String query = "UPDATE tb_anggota_keluarga SET agama = ?, id_kk = ?, jenis_kelamin = ?, kepala_keluarga = ?, nama = ?, nik = ?, pendidikan = ?, tanggal_lahir = ?, tempat_lahir =? WHERE id = ?";
        jdbcTemplate.update(query,
                new Object[] { anggotaKeluarga.getAgama(), anggotaKeluarga.getId_kk(),
                        anggotaKeluarga.getJenis_kelamin(), anggotaKeluarga.getKepala_keluarga(),
                        anggotaKeluarga.getNama(), anggotaKeluarga.getNik(), anggotaKeluarga.getPendidikan(),
                        anggotaKeluarga.getTanggal_lahir(), anggotaKeluarga.getTempat_lahir(), id });

        return anggotaKeluarga;
    }

    @Override
    public AnggotaKeluarga deleteAnggotaKeluarga(int id) {
        // TODO Auto-generated method stub
        String query = "SELECT * FROM tb_anggota_keluarga WHERE id = ?";
        var result = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(AnggotaKeluarga.class), id);

        query = "DELETE FROM tb_anggota_keluarga WHERE id = ?";
        jdbcTemplate.update(query, id);
        return result;
    }

    @Override
    public List<AnggotaKeluarga> getAllAK() {
        // TODO Auto-generated method stub
        String query = "SELECT * FROM tb_anggota_keluarga";

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(AnggotaKeluarga.class));
    }

    @Override
    public List<AnggotaKeluarga> deleteAllAnggotaKeluarga(int id) {
        String query = "SELECT * FROM tb_anggota_keluarga WHERE id = ?";
        var result = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(AnggotaKeluarga.class), id);

        query = "DELETE FROM tb_anggota_keluarga WHERE id = ?";
        jdbcTemplate.update(query, id);
        return result;
    }
}
