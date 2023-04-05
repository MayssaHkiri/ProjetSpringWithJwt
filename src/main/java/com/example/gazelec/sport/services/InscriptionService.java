package com.example.gazelec.sport.services;

import java.util.List;

import com.example.gazelec.sport.models.Inscription;

public interface InscriptionService {
	
	public  Inscription AjouterInscription (Inscription i ); 
	public List<Inscription> ConsulterInscriptions () ; 
	public Inscription ConsulterInscriptionById (Long id); 
	public void SupprimeInscriptionById (Long id); 

}
