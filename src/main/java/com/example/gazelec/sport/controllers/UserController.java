package com.example.gazelec.sport.controllers;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.gazelec.sport.models.User;
import com.example.gazelec.sport.respositories.UserRepository;
import com.example.gazelec.sport.services.UserService;



@RequestMapping ("/utilisateurs")
@CrossOrigin("*")
@RestController
public class UserController {
    
	@Autowired
	private UserRepository utilRepo ;
	@Autowired 
	private UserService userService ; 
	@Autowired
    private JavaMailSender javaMailSender;
	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/ajouter")
	public User AjouterUtilisateur (@RequestBody User user ) {
		
		
		return userService.AjouterUtilisateur(user);
	}
	@GetMapping("/Consulter")
	public  List<User> ListerUtilisateurs (){
		return userService.ConsulterUtilisateurs(); 
	}
	@GetMapping ("Rechercher/{id}")
	public User ConsulterUtilisateur ( @PathVariable Long id) {
		return userService.ConsulterUtilById(id);
	}
	@PutMapping ("Modifier/{id}")
	public User ModifierUtilisateur (@RequestBody User user )
	{
		return userService.ModifierUtilisateur(user);
	}
	@DeleteMapping ("Supprimer/{id}")
	public void SupprimerUtilisateur (@PathVariable Long id )
	{
		userService.SupprimeUtilById(id); 
	}
	  @GetMapping("/ListeDesUtilisateurs/{role}")
	     List<User> ListeParRole( @PathVariable String role){
	        return userService.ListeParRole(role);
	   }
	  @GetMapping("/RechercherAdherent/{role}/{critere}")
	     List<User> ChercherUtilisateur( @PathVariable String role ,@PathVariable String critere ){
	        return userService.RechercherAdherent(role ,critere );
	   }
	  
	  
	  @GetMapping("/ListeAvecDisciplines")
	     List<User> UtilisateurEtDiscipline(  ){
	        return userService.UtilisateursEtDiscipline();
	   }
	  
	  
	  @GetMapping("/forgot-password/{email}")
	    public boolean findUserByemail(@PathVariable String email )  {
		    boolean exist=true;
		    System.out.println("Utilisateur"+email);
	        String token = UUID.randomUUID().toString();
	       Optional <User> user = userService.FindUserByMail(email);
	        if (!user.isPresent()) {
	            System.out.println("Utilisateur n'existe pas");
	            exist=false;
	        }
	        else {
	        
	       User  userr = user.get();
	      userr.setResetPasswordToken(token);
	        utilRepo.save(userr);
	        System.out.println("Utilisateur "+userr.getEmail()); 
	        
	        String Url = "http://localhost:4200/resetPassword?token=" + token ; 
	        // Send password reset email
	        SimpleMailMessage mail = new SimpleMailMessage();
	        mail.setFrom("gazelecprojet@gmail.com");
	        mail.setTo(userr.getEmail());
	        mail.setSubject("Récupération du mot de passe");
	        mail.setText("Cher(e) "+userr.getNom()+",\n\n"
	                + "Vous avez demandé la récupération de votre mot de passe pour votre compte adhérent. Veuillez cliquer sur le lien ci-dessous pour procéder à la récupération :\n\n"
	                + Url + "\n\n"
	              
	                + "L'équipe de La Gazelec");
	        javaMailSender.send(mail);
	       
	        }   return exist;
	    }
	  @GetMapping("/resetPassword/{resetToken}/{password}")
	    public boolean findUserByResetToken (@PathVariable String resetToken,@PathVariable String password) {
		  boolean existToken=true;
		  
		 System.out.println("Get  User By resetToken.."+resetToken);
		 System.out.println("Mot de passe"+password);
	            
	             Optional<User> user = userService.FindUserByToken(resetToken);
	             if (!user.isPresent()) {
	 				System.out.println( "We didn't find an account for that Token");
	 				existToken=false;
	 			} else {
	 				User userr = user.get();
	 			
	 				
	 				userr.setPassword( encoder.encode(password.trim()));
	 				userr.setResetPasswordToken(null);
	 				 System.out.println("Mot de passe"+userr.getPassword());
	 				 utilRepo.save(userr);
	 				
	      			
	 			}return existToken;
	   }
	


	  
	
}
