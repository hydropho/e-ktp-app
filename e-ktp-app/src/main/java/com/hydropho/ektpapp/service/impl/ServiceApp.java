package com.hydropho.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hydropho.springboot.model.AnggotaKeluarga;
import com.hydropho.springboot.model.KartuKeluarga;
import com.hydropho.springboot.model.User;
import com.hydropho.springboot.repository.IRepository;
import com.hydropho.springboot.service.IService;

@Service
public class ServiceApp implements IService {

    @Autowired
    IRepository repos;

    @Override
    public List<User> getAllUser() {
        // TODO Auto-generated method stub
        return repos.getAllUser();
    }

    @Override
    public User insertUser(User user) {
        // TODO Auto-generated method stub
        return repos.insertUser(user);
    }

    @Override
    public User getSelectedUser(int id) {
        // TODO Auto-generated method stub
        return repos.getSelectedUser(id);
    }

    @Override
    public User updateUser(int id, User user) {
        // TODO Auto-generated method stub
        return repos.updateUser(id, user);
    }

    @Override
    public User deleteUser(int id) {
        // TODO Auto-generated method stub
        return repos.deleteUser(id);
    }

    @Override
    public List<User> getFiveUsers() {
        // TODO Auto-generated method stub
        return repos.getFiveUsers();
    }

    @Override
    public User login(User user) {
        // TODO Auto-generated method stub
        return repos.login(user);
    }

    @Override
    public User register(User user) {
        // TODO Auto-generated method stub
        return repos.register(user);
    }

    @Override
    public List<KartuKeluarga> getAllKK() {
        // TODO Auto-generated method stub
        return repos.getAllKK();
    }

    @Override
    public KartuKeluarga insertKK(KartuKeluarga kartuKeluarga) {
        // TODO Auto-generated method stub
        return repos.insertKK(kartuKeluarga);
    }

    @Override
    public KartuKeluarga getSelectedKK(int id) {
        // TODO Auto-generated method stub
        return repos.getSelectedKK(id);
    }

    @Override
    public KartuKeluarga updateKK(int id, KartuKeluarga kartuKeluarga) {
        // TODO Auto-generated method stub
        return repos.updateKK(id, kartuKeluarga);
    }

    @Override
    public KartuKeluarga deleteKK(int id) {
        // TODO Auto-generated method stub
        return repos.deleteKK(id);
    }

    @Override
    public List<KartuKeluarga> getFiveKK() {
        // TODO Auto-generated method stub
        return repos.getFiveKK();
    }

    @Override
    public List<AnggotaKeluarga> getAllAnggotaKeluarga(int id) {
        // TODO Auto-generated method stub
        return repos.getAllAnggotaKeluarga(id);
    }

    @Override
    public AnggotaKeluarga insertAnggotaKeluarga(AnggotaKeluarga anggotaKeluarga) {
        // TODO Auto-generated method stub
        return repos.insertAnggotaKeluarga(anggotaKeluarga);
    }

    @Override
    public AnggotaKeluarga getSelectedAnggotaKeluarga(int id) {
        // TODO Auto-generated method stub
        return repos.getSelectedAnggotaKeluarga(id);
    }

    @Override
    public AnggotaKeluarga updateAnggotaKeluarga(int id, AnggotaKeluarga anggotaKeluarga) {
        // TODO Auto-generated method stub
        return repos.updateAnggotaKeluarga(id, anggotaKeluarga);
    }

    @Override
    public AnggotaKeluarga deleteAnggotaKeluarga(int id) {
        // TODO Auto-generated method stub
        return repos.deleteAnggotaKeluarga(id);
    }

    @Override
    public List<AnggotaKeluarga> getAllAK() {
        // TODO Auto-generated method stub
        return repos.getAllAK();
    }

    @Override
    public List<AnggotaKeluarga> deleteAllAnggotaKeluarga(int id) {
        // TODO Auto-generated method stub
        return repos.deleteAllAnggotaKeluarga(id);
    }

}
