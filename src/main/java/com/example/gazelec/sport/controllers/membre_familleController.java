package com.example.gazelec.sport.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gazelec.sport.models.AddUserRequest;
import com.example.gazelec.sport.models.Discipline;
import com.example.gazelec.sport.models.ERole;
import com.example.gazelec.sport.models.MessageResponse;
import com.example.gazelec.sport.models.Role;
import com.example.gazelec.sport.models.User;
import com.example.gazelec.sport.models.membre_famille;
import com.example.gazelec.sport.respositories.UserRepository;
import com.example.gazelec.sport.services.UserService;
import com.example.gazelec.sport.services.membre_familleService;

@RequestMapping ("/famille")
@CrossOrigin("*")
@RestController
public class membre_familleController {
	
	@Autowired
	private membre_familleService MembreService;
	
	@Autowired
	private UserService userService ;
	
	@PostMapping("/ajouter")
	public membre_famille ajouterMembre(@RequestBody membre_famille m, @RequestParam long id) {
		 User u =new User ();
			u= userService.ConsulterUtilById(id);
			m.setUser(u);
	    return MembreService.Ajoutermembre(m);
	}
	@GetMapping("/Consulter")
	public  List<membre_famille> ListerMembres (){
		return MembreService.Consultermembres();
	}
	@GetMapping ("Consulter/{id}")
	public membre_famille ConsulterMembre ( @PathVariable Long id) {
		return MembreService.ConsulterMembreById(id);
	}
	
	@DeleteMapping ("Supprimer/{id}")
	public void SupprimerMembre (@PathVariable Long id )
	{
		MembreService.SupprimeMembreById(id);
	}
	
	
}
