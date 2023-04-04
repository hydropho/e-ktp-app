package com.hydropho.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hydropho.springboot.model.AnggotaKeluarga;
import com.hydropho.springboot.model.KartuKeluarga;
import com.hydropho.springboot.model.User;
import com.hydropho.springboot.service.IService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")

@RequestMapping("/api")
public class ControllerApp {

    @Autowired
    IService service;

    // =================== USER =====================
    @GetMapping("/user/getAll")
    public List<User> getAllUser() {
        return service.getAllUser();
    }

    @GetMapping("/user/getUser/{id}")
    public User getSelectedUser(@PathVariable int id, @RequestBody User user) {
        return service.getSelectedUser(id);
    }

    @PutMapping("/user/update/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        return service.updateUser(id, user);
    }

    @DeleteMapping("/user/delete/{id}")
    public User deleteUser(@PathVariable int id) {
        return service.deleteUser(id);
    }

    @GetMapping("/user/getFiveUser")
    public List<User> getFiveUsers() {
        return service.getFiveUsers();
    }

    @PostMapping("/user/login")
    public User login(@RequestBody User user) {
        return service.login(user);
    }

    @PostMapping("/user/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

    // =================== KartuKeluarga =====================
    @GetMapping("/kartu-keluarga/getAll")
    public List<KartuKeluarga> getAllKK() {
        return service.getAllKK();
    }

    @PostMapping("/kartu-keluarga/insert")
    public KartuKeluarga insertKK(@RequestBody KartuKeluarga kartuKeluarga) {
        return service.insertKK(kartuKeluarga);
    }

    @GetMapping("/kartu-keluarga/getKK/{id}")
    public KartuKeluarga getSelectedKK(@PathVariable int id) {
        return service.getSelectedKK(id);
    }

    @PutMapping("/kartu-keluarga/update/{id}")
    public KartuKeluarga updateKK(@PathVariable int id, @RequestBody KartuKeluarga kartuKeluarga) {
        return service.updateKK(id, kartuKeluarga);
    }

    @DeleteMapping("/kartu-keluarga/delete/{id}")
    public KartuKeluarga deleteKK(@PathVariable int id) {
        return service.deleteKK(id);
    }

    @GetMapping("/kartu-keluarga/getFiveKK")
    public List<KartuKeluarga> getFiveKK() {
        return service.getFiveKK();
    }

    // =================== AnggotaKeluarga =====================
    @GetMapping("/anggota-keluarga/getAll")
    public List<AnggotaKeluarga> getAllAnggotaKeluarga() {
        return service.getAllAK();
    }

    @GetMapping("/anggota-keluarga/getAllById/{id}")
    public List<AnggotaKeluarga> getAllAnggotaKeluarga(@PathVariable int id) {
        return service.getAllAnggotaKeluarga(id);
    }

    @PostMapping("/anggota-keluarga/insert")
    public AnggotaKeluarga insertAnggotaKeluarga(@RequestBody AnggotaKeluarga anggotaKeluarga) {
        return service.insertAnggotaKeluarga(anggotaKeluarga);
    }

    @GetMapping("/anggota-keluarga/getAnggotaKeluarga/{id}")
    public AnggotaKeluarga getSelectedAnggotaKeluarga(@PathVariable int id) {
        return service.getSelectedAnggotaKeluarga(id);
    }

    @PutMapping("/anggota-keluarga/update/{id}")
    public AnggotaKeluarga updateAnggotaKeluarga(@PathVariable int id, @RequestBody AnggotaKeluarga anggotaKeluarga) {
        return service.updateAnggotaKeluarga(id, anggotaKeluarga);
    }

    @DeleteMapping("/anggota-keluarga/delete/{id}")
    public AnggotaKeluarga deleteAnggotaKeluarga(@PathVariable int id) {
        return service.deleteAnggotaKeluarga(id);
    }

    @DeleteMapping("/anggota-keluarga/deleteAll/{id}")
    public List<AnggotaKeluarga> deleteAllAnggotaKeluarga(@PathVariable int id) {
        return service.deleteAllAnggotaKeluarga(id);
    }

}
