package com.example.gazelec.sport.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gazelec.sport.models.User;
import com.example.gazelec.sport.models.membre_famille;
import com.example.gazelec.sport.respositories.InscriptionRepository;
import com.example.gazelec.sport.respositories.UserRepository;
import com.example.gazelec.sport.respositories.membre_familleRepository;
import com.example.gazelec.sport.models.Discipline;
import com.example.gazelec.sport.models.Inscription;
import com.example.gazelec.sport.services.UserService;
import com.example.gazelec.sport.services.DisciplineService;
import com.example.gazelec.sport.services.InscriptionService;

@RequestMapping ("/inscription")
@CrossOrigin("*")
@RestController
public class InscriptionController {
	
	@Autowired
	private InscriptionService InscriService;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	private UserService userService ;
	
	@Autowired
	private DisciplineService DisciplineService ;
	@Autowired 
	UserRepository utilRepo ;
	@Autowired 
	membre_familleRepository MembreRepo ;
	
	@Autowired
	private InscriptionRepository InscriRepo ;
	
	
	@PostMapping("/ajouter")
	public Inscription ajouterMembre(@RequestBody Inscription i, @RequestParam long id ,  @RequestParam long idD ,  @RequestParam String role) {
		
		if("adherent".equals(role))
		{User u =new User ();
		System.out.println("voula le role"+role);
		 Optional<User> optionalUser = utilRepo.findById(id);
		 System.out.println("voula l'id user"+id);
			    if (optionalUser.isPresent()) { 
			        User existingUser = optionalUser.get();
			        i.setUser(existingUser);
			        System.out.println("voula l'id user"+existingUser.getNom());
			        }}
		 else {
			 System.out.println("voula le role"+role);
			 membre_famille mb= new membre_famille ();
			  Optional<membre_famille> optionalMembre = MembreRepo.findById(id);
			  if (optionalMembre.isPresent()) { 
			        membre_famille existingMembre = optionalMembre.get();
			        i.setMembre_famille(existingMembre);
			        }
			 
		 }
			
		Discipline d =new Discipline();
		d=DisciplineService.ConsulterDisciplineById(idD);
		
			
			i.setDiscipline(d);
			i.setDate_inscription(LocalDate.now());
			i.setStatus("en attente");
	    return InscriService.AjouterInscription(i);
	}
	@GetMapping("/Consulter")
	public  List<Inscription> ListerInscriptions (){
		return InscriService.ConsulterInscriptions();
	}
	@GetMapping ("Consulter/{id}")
	public Inscription ConsulterInscription ( @PathVariable Long id) {
		return InscriService.ConsulterInscriptionById(id);
	}
	
	@DeleteMapping ("Supprimer/{id}")
	public void SupprimerInscription (@PathVariable Long id )
	{
		InscriService.SupprimeInscriptionById(id);
	}
	
	@GetMapping("/validation/{id}/{email}")
    public boolean validerMembre(@PathVariable long id ,@PathVariable String email )  {
	    boolean exist=true;
	 Optional <User> user = userService.FindUserByMail(email);
        if (!user.isPresent()) {
            System.out.println("Utilisateur n'existe pas");
            exist=false;
        }
        else { Optional <Inscription> inscri = InscriRepo.findById(id) ;
        	Inscription ins =inscri.get();
        	
        	ins.setStatus("accepte");
        	InscriRepo.save(ins);      
       User  userr = user.get();
       
       
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("gazelecprojet@gmail.com");
        mail.setTo(userr.getEmail());
        mail.setSubject("Validation d'inscription");
        mail.setText("Félicitations , vous etes membre dans le club sportif de la gazelec : \n "  );
        System.out.println("Mail "+mail);
        javaMailSender.send(mail);
       
        }   return exist;
    }
	@GetMapping("/refus/{email}")
    public boolean RefuserMembre(@PathVariable String email )  {
	    boolean exist=true;
	    
       
       Optional <User> user = userService.FindUserByMail(email);
        if (!user.isPresent()) {
            System.out.println("Utilisateur n'existe pas");
            exist=false;
        }
        else {
        
       User  userr = user.get();
       
        // Send password reset email
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("gazelecprojet@gmail.com");
        mail.setTo(userr.getEmail());
        mail.setSubject("Validation d'inscription");
        mail.setText("Demande d'inscription refusé , vous pouvez réessayer l'inscription : \n "  );
        System.out.println("Mail "+mail);
        javaMailSender.send(mail);
       
        } 
        return exist;
    }
	  @GetMapping("/demandes-inscription")
	    public List<Object[]> getDemandesInscription() {
	        return InscriRepo.DemandeInscription();
	    }
	   
	    @GetMapping("/RechercheInscriptions/{recherche}")
	    List<Object[]> RechercherInscription( @PathVariable String recherche  ){
	        return InscriRepo.RechercheInscription(recherche);
	   }

	   
	    @GetMapping("/ListeDesMembres")
	    public List<Object[]> ListeDesMembres() {
	        return InscriRepo.ListeDesmembres();
	    }
	   
	    @GetMapping("/RechercherMembres/{recherche}")
	    List<Object[]> RechercherMembres( @PathVariable String recherche  ){
	        return InscriRepo.RechercherMembre(recherche);
	   }
	   
	    @GetMapping("/EmailSuppression/{email}")
	    public boolean SupprimerrMembre(@PathVariable String email )  {
		    boolean exist=true;
		    
	       
	       Optional <User> user = userService.FindUserByMail(email);
	        if (!user.isPresent()) {
	            System.out.println("Utilisateur n'existe pas");
	            exist=false;
	        }
	        else {
	        
	       User  userr = user.get();
	       
	        // Send password reset email
	        SimpleMailMessage mail = new SimpleMailMessage();
	        mail.setFrom("gazelecprojet@gmail.com");
	        mail.setTo(userr.getEmail());
	        mail.setSubject("Iinscription dans la discipline sportif de la gazelec");
	        mail.setText("Vous n'etes plus un membre dans l'équioe sportive de la gazelec  : \n "  );
	        System.out.println("Mail "+mail);
	        javaMailSender.send(mail);
	       
	        } 
	        return exist;
	    }
	


	    // methode pour consulter les membres de la meme discipline que le moderateur connecté 
	  @GetMapping ("/membres/{discipline}")
	  List<Object[]> ConsulterMembresByModerateur (@PathVariable String discipline ) {
		  return InscriRepo.ConsulterMembresByModerateur(discipline) ; 
	  }
     // methode pour rechercher les membres par le moderateur 
	  @GetMapping ("/rechercheMembres/{critere}/{discipline}") 
	  List<Object[]> RechercherByModerateur (@PathVariable String critere , @PathVariable String discipline ) {
		  return InscriRepo.RechercheMembresParModerateur(critere, discipline) ; 
	  }
	  //liste d'inscription pour ajouter les disciplines dans le profil 
	  @GetMapping("/users/{userId}/disciplines")
	    public List<Discipline> getDisciplinesByUserId(@PathVariable Long userId) {
	        return InscriRepo.findDisciplinesByUserId(userId);
	    }

}
