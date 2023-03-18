package com.example.gazelec.sport.services;

import java.util.List;

import com.example.gazelec.sport.models.Evénement;

public interface EvénementService {
	public Evénement AjouterEvénement( Evénement E ); 
	public List<Evénement> ListeDesEvénements () ; 
	public Evénement ConsulterEvénementById (Long id); 
	public void SupprimerEvénement (Long id); 
}
