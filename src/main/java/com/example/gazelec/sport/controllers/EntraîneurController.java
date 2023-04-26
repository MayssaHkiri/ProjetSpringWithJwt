package com.example.gazelec.sport.controllers;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gazelec.sport.models.Entraîneur;
import com.example.gazelec.sport.models.MessageResponse;
import com.example.gazelec.sport.models.User;
import com.example.gazelec.sport.respositories.EntraîneurRepository;
import com.example.gazelec.sport.services.EntraîneurService;

@RequestMapping ("/entraineurs")
@CrossOrigin("*")
@RestController
public class EntraîneurController {
	@Autowired
	private EntraîneurService EnterService;
	@Autowired 
	private EntraîneurRepository entraineurRepo ; 
	
	@PostMapping("/ajouter/{id}")
	public  ResponseEntity<?>  ajouterEntraineur (@RequestBody Entraîneur  E , @PathVariable  Long id  )
	{   
		if (entraineurRepo.existsByEmail(E.getEmail())) {
			   return ResponseEntity.badRequest().body(new MessageResponse("Error : Email is already taken ! ")) ; 
		   }
		else {
			
			EnterService.AjouterEntraîneur(E , id );
			 return ResponseEntity.ok(new MessageResponse("Entraineur registered successfully!"));
		}
	}
	@GetMapping("/Consulter")
	public  List<Entraîneur> ListerUtilisateurs (){
		return EnterService.ConsulterEntraîneurs();
	}
	@GetMapping("/consultation/{discipline}")
	 public List<Entraîneur> ListeEntraineursetDisciplines(@PathVariable String discipline ){
		return EnterService.ListeEntraineurs(discipline); 
	}
	@GetMapping ("/{id}")
	public Entraîneur ConsulterUtilisateur ( @PathVariable Long id) {
		return EnterService.ConsulterEntraîneurById(id);
	}
	@PutMapping ("Modifier")
	public Entraîneur ModifierUtilisateur (@RequestBody Entraîneur En )
	{
		return EnterService.ModifierEntraîneur(En );
	}
	@DeleteMapping ("/{id}")
	public void SupprimerUtilisateur (@PathVariable Long id )
	{
		EnterService.SupprimeEntraîneurById(id);
	}
	@GetMapping("/ExistEmail/{email}")
	   public boolean existmail (@PathVariable String email )
	   {
		    boolean exist=false;
		    if(entraineurRepo.existsByEmail(email))
		    {exist=true;}
		    
		    return exist;
	   }
	@GetMapping("/rechercher/{critere}/{discipline}")
	public List<Entraîneur> RechercherEntraineurs (@PathVariable String  critere , @PathVariable String discipline   ) {
		 return EnterService.RechercherEntraineurs(critere , discipline ) ; 
	}

}
