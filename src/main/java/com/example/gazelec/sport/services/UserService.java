package com.example.gazelec.sport.services;

import java.util.List;
import java.util.Optional;

import com.example.gazelec.sport.models.User;

public interface UserService {
 
	public  User AjouterUtilisateur(  User  U  ); 
	public List< User> ConsulterUtilisateurs () ; 
	public  User ConsulterUtilById (Long id); 
	public void SupprimeUtilById (Long id); 
	public  User ModifierUtilisateur (  User U  ); 
	public List< User>ListeParRole (String RoleNom);
	
	
	public List< User>UtilisateursEtDiscipline ();
	public Optional<User>FindUserByMail (String email);
	public Optional<User>FindUserByToken (String token);
	List<User> RechercherAdherent(String RoleNom, String recherche);
	
}
