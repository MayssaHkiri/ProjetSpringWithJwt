package com.example.spring.camping.servicesImpl;

import com.example.spring.camping.models.ManageUsers.Role;
import com.example.spring.camping.respositories.RoleRepository;
import com.example.spring.camping.services.ICrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements ICrud<Role> {

    @Autowired
    private RoleRepository roleRepo ;
    @Override
    public List<Role> getAll() {
        return roleRepo.findAll() ;
    }

    @Override
    public Role getById(long id) {
        return roleRepo.getById(id);
    }

    @Override
    public Role add(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void delete(long id) {
      roleRepo.deleteById(id);
    }

    @Override
    public Role update(Role role) {
        return roleRepo.save(role);
    }
}
