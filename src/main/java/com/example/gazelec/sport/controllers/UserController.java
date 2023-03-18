package com.example.gazelec.sport.controllers;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.gazelec.sport.models.User;

import com.example.gazelec.sport.services.UserService;



@RequestMapping ("/utilisateurs")
@CrossOrigin("*")
@RestController
public class UserController {
    
	
	@Autowired 
	private UserService userService ; 
	@PostMapping("/ajouter")
	public User AjouterUtilisateur (@RequestBody User user ) {
		return userService.AjouterUtilisateur(user);
	}
	@GetMapping("/Consulter")
	public  List<User> ListerUtilisateurs (){
		return userService.ConsulterUtilisateurs(); 
	}
	@GetMapping ("Rechercher/{id}")
	public User ConsulterUtilisateur ( @PathVariable Long id) {
		return userService.ConsulterUtilById(id);
	}
	@PutMapping ("Modifier/{id}")
	public User ModifierUtilisateur (@RequestBody User user )
	{
		return userService.ModifierUtilisateur(user);
	}
	@DeleteMapping ("Supprimer/{id}")
	public void SupprimerUtilisateur (@PathVariable Long id )
	{
		userService.SupprimeUtilById(id); 
	}
	  @GetMapping("/ListeDesUtilisateurs/{role}")
	     List<User> ListeParRole( @PathVariable String role){
	        return userService.ListeParRole(role);
	   }
	  @GetMapping("/RechercherUtilisateur/{role}/{critere}")
	     List<User> ChercherUtilisateur( @PathVariable String role ,@PathVariable String critere ){
	        return userService.RechercherUtilisateur(role ,critere );
	   }
	  @GetMapping("/ListeAvecDisciplines/{role}")
	     List<User> UtilisateurEtDiscipline( @PathVariable String role  ){
	        return userService.UtilisateursEtDiscipline(role);
	   }
	
}
