package com.hydropho.springboot.service;

import java.util.List;

import com.hydropho.springboot.model.AnggotaKeluarga;
import com.hydropho.springboot.model.KartuKeluarga;
import com.hydropho.springboot.model.User;

public interface IService {
    // ============= USER ================
    public List<User> getAllUser();

    public User insertUser(User user);

    public User getSelectedUser(int id);

    public User updateUser(int id, User user);

    public User deleteUser(int id);

    public List<User> getFiveUsers();

    public User login(User user);

    public User register(User user);

    // ============= KartuKeluarga ================
    public List<KartuKeluarga> getAllKK();

    public KartuKeluarga insertKK(KartuKeluarga kartuKeluarga);

    public KartuKeluarga getSelectedKK(int id);

    public KartuKeluarga updateKK(int id, KartuKeluarga kartuKeluarga);

    public KartuKeluarga deleteKK(int id);

    public List<KartuKeluarga> getFiveKK();

    // ============== AnggotaKeluarga =================
    public List<AnggotaKeluarga> getAllAK();

    public List<AnggotaKeluarga> getAllAnggotaKeluarga(int id);

    public AnggotaKeluarga insertAnggotaKeluarga(AnggotaKeluarga anggotaKeluarga);

    public AnggotaKeluarga getSelectedAnggotaKeluarga(int id);

    public AnggotaKeluarga updateAnggotaKeluarga(int id, AnggotaKeluarga anggotaKeluarga);

    public AnggotaKeluarga deleteAnggotaKeluarga(int id);

    public List<AnggotaKeluarga> deleteAllAnggotaKeluarga(int id);
}
