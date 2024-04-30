package com.example.spring.camping.respositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.camping.models.ERole;
import com.example.spring.camping.models.ManageUsers.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String name);
}