package com.example.gazelec.sport.services;

import java.util.List;

import com.example.gazelec.sport.models.Entraîneur;

public interface EntraîneurService {
	public  Entraîneur AjouterEntraîneur(Entraîneur e ); 
	public List<Entraîneur> ConsulterEntraîneurs () ; 
	public Entraîneur ConsulterEntraîneurById (Long id); 
	public void SupprimeEntraîneurById (Long id); 
	public Entraîneur ModifierEntraîneur (Entraîneur En ); 
}
