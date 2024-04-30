package com.example.spring.camping.services;

import java.util.List;
import java.util.Optional;

import com.example.spring.camping.models.ManageUsers.User;

public interface UserService {
 
	public  User AjouterUtilisateur(  User  U  ); 
	public List< User> ConsulterUtilisateurs () ; 
	public  User ConsulterUtilById (Long id); 
	public void SupprimeUtilById (Long id); 
	public  User ModifierUtilisateur (  User U  ); 
	public List< User>ListeParRole (String RoleNom);
	

	
}
