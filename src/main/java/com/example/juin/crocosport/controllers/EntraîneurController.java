package com.example.juin.crocosport.controllers;

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

import com.example.juin.crocosport.models.Entraîneur;
import com.example.juin.crocosport.services.EntraîneurService;

@RequestMapping ("/entraineurs")
@CrossOrigin("*")
@RestController
public class EntraîneurController {
	@Autowired
	private EntraîneurService EnterService;
	
	@PostMapping("/ajouter")
	public Entraîneur ajouterEvénement (@RequestBody Entraîneur  E)
	{
		return EnterService.AjouterEntraîneur(E);
	}
	@GetMapping("/Consulter")
	public  List<Entraîneur> ListerUtilisateurs (){
		return EnterService.ConsulterEntraîneurs();
	}
	@GetMapping ("Rechercher/{id}")
	public Entraîneur ConsulterUtilisateur ( @PathVariable Long id) {
		return EnterService.ConsulterEntraîneurById(id);
	}
	@PutMapping ("Modifier/{id}")
	public Entraîneur ModifierUtilisateur (@RequestBody Entraîneur En )
	{
		return EnterService.ModifierEntraîneur(En);
	}
	@DeleteMapping ("Supprimer/{id}")
	public void SupprimerUtilisateur (@PathVariable Long id )
	{
		EnterService.SupprimeEntraîneurById(id);
	}
	

}
