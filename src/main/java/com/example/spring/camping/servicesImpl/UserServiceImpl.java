package com.example.spring.camping.servicesImpl;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import com.example.spring.camping.models.ERole;
import com.example.spring.camping.models.ManageUsers.AddUserRequest;
import com.example.spring.camping.models.ManageUsers.MessageResponse;
import com.example.spring.camping.models.ManageUsers.Role;
import com.example.spring.camping.respositories.RoleRepository;
import com.example.spring.camping.services.ICrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.spring.camping.models.ManageUsers.User;
import com.example.spring.camping.respositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service 

public class UserServiceImpl implements ICrud<User>  {
	
	@Autowired
	private UserRepository utilRepo ;
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private JavaMailSender javaMailSender;


	public ResponseEntity<?> addNewUser (@Valid @RequestBody AddUserRequest addUserRequest )
	{

		if (utilRepo.existsByEmail(addUserRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error : Email is already taken ! ")) ;
		}

		// add new user
		User user = new User (addUserRequest.getFirstName() , addUserRequest.getLastName() ,
				addUserRequest.getEmail() , addUserRequest.getAdresse(), addUserRequest.getDate_naissance(),addUserRequest.getTelephone(),encoder.encode(addUserRequest.getPassword()));

		String strRole="";
		strRole = addUserRequest.getRole();

		// affecter un role à l'utilisateur
		if (strRole == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error : Role non connu  ! ")) ;
		}
		else {
			switch (strRole) {
				case "CAMPEUR":

					Role RoleCAMPEUR = roleRepository.findByName("CAMPEUR")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					user.setRole(RoleCAMPEUR);


					System.out.println("here role CAMPEUR " + strRole);
					break;
				case "CENTRECAMPING":

					Role RoleCENTRECAMPING = roleRepository.findByName("CENTRECAMPING")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					user.setRole(RoleCENTRECAMPING);


					System.out.println("here role CENTRECAMPEUR " + strRole);
					break;
				case "ADMIN":

					Role RoleADMIN = roleRepository.findByName("ADMIN")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					user.setRole(RoleADMIN);
					break;


				default:

					Role role = roleRepository.findByName(strRole)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					user.setRole(role);
			}
		}

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("geeksjava44@gmail.com");
		mail.setTo(addUserRequest.getEmail());
		mail.setSubject("Nouveau mot de passe");
		mail.setText("Cher(e) "+addUserRequest.getLastName()+",\n\n"
				+ "Vous êtes un nouveau utilisateur chez notre platform de camping \n\n"

				+ "Veuillez vous connecter à votre compte en utilisant votre mot de passe . \n\n"
				+ "Si vous avez des questions ou besoin d'assistance, n'hésitez pas à nous contacter.\n\n"

				+ "Cordialement,\n"
				+ "Notre équipe");
		javaMailSender.send(mail);
		utilRepo.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

	}
	public List<User> Rechercher(String RoleNom ,String recherche ) {
		
		return utilRepo.RechercherAdherent(RoleNom ,recherche);
	}


	public Optional<User> FindUserByMail(String email) {
		
		return utilRepo.findByEmail(email);
	}

	public Optional<User> FindUserByToken(String token) {
		
		return utilRepo.findByResetToken(token);
	}


	@Override
	public List<User> getAll() {
		return utilRepo.findAll();
	}

	@Override
	public User getById(long id) {
		Optional<User> m = utilRepo.findById(id) ;
		return m.isPresent() ? m.get() : null ;
	}

	@Override
	public User add(User user) {
		return null;
	}


	@Override
	public void delete(long id) {
		utilRepo.deleteById(id);
	}

	@Override
	public User update(User user) {
		return utilRepo.save(user);
	}



}

