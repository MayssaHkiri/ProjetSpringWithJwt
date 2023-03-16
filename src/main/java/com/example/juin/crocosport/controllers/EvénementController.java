package com.example.juin.crocosport.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.juin.crocosport.services.EvénementService;
import com.example.juin.crocosport.models.Evénement;

@RequestMapping ("/events")
@CrossOrigin("*")
@RestController
public class EvénementController {
	@Autowired
	private EvénementService EvénementService;
	
	@PostMapping("/ajouter")
	public Evénement ajouterEvénement (@RequestBody Evénement E)
	{
		return EvénementService.AjouterEvénement(E);
	}
	@GetMapping("/Liste")
	public List<Evénement> ListeDesEvénements ()
	{
		 return EvénementService.ListeDesEvénements();
	}
	@GetMapping("/Consulter/{id}")
	public Evénement ConsulterEvénement (@PathVariable long id)
	{
		return EvénementService.ConsulterEvénementById(id);
	}
	@DeleteMapping("/Supprimer/{id}")
	public void SupprimerEvénement (@PathVariable long id)
	{
		EvénementService.SupprimerEvénement(id);
	}
}
