package com.example.spring.camping.controllers;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.spring.camping.servicesImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import com.example.spring.camping.models.ManageUsers.User;
import com.example.spring.camping.respositories.UserRepository;


@RequestMapping("/utilisateurs")
@CrossOrigin("*")
@RestController
public class UserController {

    @Autowired
    private UserRepository utilRepo;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    PasswordEncoder encoder;


    @GetMapping("/Consulter")
    public List<User> ListerUtilisateurs() {
        return userServiceImpl.getAll();
    }

    @GetMapping("Rechercher/{id}")
    public User ConsulterUtilisateur(@PathVariable Long id) {
        return userServiceImpl.getById(id);
    }

    @PutMapping("Modifier/{id}")
    public User ModifierUtilisateur(@RequestBody User user) {
        return userServiceImpl.update(user);
    }

    @DeleteMapping("Supprimer/{id}")
    public void SupprimerUtilisateur(@PathVariable Long id) {
        userServiceImpl.delete(id);
    }



}
