package com.example.gazelec.sport.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.gazelec.sport.models.User;
import com.example.gazelec.sport.respositories.UserRepository;
import com.example.gazelec.sport.services.UserService;


@Service 

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository utilRepo ; 
	
	
	

	@Override
	public User AjouterUtilisateur(User U) {
		
		return  utilRepo.save(U);
	}

	@Override
	public List<User> ConsulterUtilisateurs() {
		
		return utilRepo.findAll();
	}

	@Override
	public User ConsulterUtilById(Long id) {
		
		 Optional<User> m = utilRepo.findById(id) ;
		 return m.isPresent() ? m.get() : null ; 
	}

	@Override
	public void SupprimeUtilById(Long id) {
		utilRepo.deleteById(id);
		
	}

	@Override
	public User ModifierUtilisateur(User U) {
		return utilRepo.save(U);
	}

	@Override
	public List<User> ListeParRole(String RoleNom) {
		
		return utilRepo.ListByRole(RoleNom);
	}

	@Override
	public List<User> RechercherUtilisateur(String RoleNom ,String recherche  ) {
		
		return utilRepo.RechercherUtilisateur(RoleNom ,recherche);
	}

	@Override
	public List<User> UtilisateursEtDiscipline(String RoleNom) {
		
		return utilRepo.ListeUtilisateursetDisciplines(RoleNom);
	}

	@Override
	public Optional<User> FindUserByMail(String email) {
		
		return utilRepo.findByEmail(email);
	}

	@Override
	public Optional<User> FindUserByToken(String token) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	
	

	

}

