package com.example.gazelec.sport.services;

import java.util.List;

import com.example.gazelec.sport.models.Entraîneur;
import com.example.gazelec.sport.models.User;

public interface EntraîneurService {
	public  Entraîneur AjouterEntraîneur(Entraîneur e , Long id  ); 
	public List<Entraîneur> ConsulterEntraîneurs () ; 
	public Entraîneur ConsulterEntraîneurById (Long id); 
	public void SupprimeEntraîneurById (Long id); 
	public Entraîneur ModifierEntraîneur (Entraîneur En  ); 
	public List<Entraîneur> ListeEntraineurs(); 
	public List<Entraîneur> RechercherEntraineurs (String recherche ) ;
}
