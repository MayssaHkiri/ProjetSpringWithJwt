package com.example.spring.camping.controllers;

import com.example.spring.camping.models.ManageUsers.Role;

import com.example.spring.camping.servicesImpl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/roles")
@CrossOrigin("*")
@RestController
public class RoleController {
    @Autowired
    private RoleServiceImpl roleService;
    
    @PostMapping("/add")
    public Role addRole (@RequestBody Role role){
        return roleService.add(role);
    }
        

    @GetMapping("/Consulter")
    public List<Role> ListerUtilisateurs() {
        return roleService.getAll();
    }

    @GetMapping("Rechercher/{id}")
    public Role ConsulterUtilisateur(@PathVariable Long id) {
        return roleService.getById(id);
    }

    @PutMapping("Modifier/{id}")
    public Role ModifierUtilisateur(@RequestBody Role Role) {
        return roleService.update(Role);
    }

    @DeleteMapping("Supprimer/{id}")
    public void SupprimerUtilisateur(@PathVariable Long id) {
        roleService.delete(id);
    }
}
