package com.example.gazelec.sport.controllers;

import java.time.LocalDate;

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
		{
		
		 Optional<User> optionalUser = utilRepo.findById(id);
		
			    if (optionalUser.isPresent()) { 
			        User existingUser = optionalUser.get();
			        i.setUser(existingUser);
			        System.out.println("voula l'id user"+existingUser.getNom());
			        }}
		 else {
			 System.out.println("voula le role"+role);
			
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
       mail.setSubject("Félicitations pour votre inscription au Club Sportif La Gazelec");
       mail.setText("Cher "+userr.getNom()+" ,\n\n"
               + "Nous sommes ravis de vous annoncer que vous êtes officiellement devenu membre du Club Sportif La Gazelec à la discipline "+ins.getDiscipline().getDiscipline()+" ! \n\n"
               + "En tant que membre, vous bénéficiez désormais d'un accès exclusif à nos installations sportives de qualité et à nos diverses activités.\n"
               + "Que ce soit pour pratiquer votre discipline préférée, participer à des compétitions ou simplement profiter d'un environnement sportif convivial, nous sommes là pour répondre à vos attentes.\n\n"
               + "N'hésitez pas à contacter notre administration pour toute question ou demande d'informations supplémentaires. Nous serons ravis de vous aider.\n"
               + "Vous pouvez nous joindre par téléphone au 71287053 ou par e-mail à gazelecprojet@gmail.com.\n\n"
               + "Encore une fois, félicitations et bienvenue dans notre club ! Nous sommes impatients de vous voir participer à nos activités sportives.\n\n"
               + "Sportivement,\n"
               + "L'équipe du Club Sportif La Gazelec");
       System.out.println("Mail : " + mail);
        javaMailSender.send(mail);
       
        }   return exist;
    }
	@GetMapping("/refus/{id}/{email}")
    public boolean RefuserMembre(@PathVariable long id ,@PathVariable String email )  {
	    boolean exist=true;
	    
       
       Optional <User> user = userService.FindUserByMail(email);
        if (!user.isPresent()) {
            System.out.println("Utilisateur n'existe pas");
            exist=false;
        }
        else {
        	Optional <Inscription> inscri = InscriRepo.findById(id) ;
        	Inscription ins =inscri.get();
        	
        	ins.setStatus("refuse");
        	InscriRepo.save(ins);
       User  userr = user.get();
       
        // Send password reset email
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("gazelecprojet@gmail.com");
        mail.setTo(userr.getEmail());
        mail.setSubject("Refus de demande d'inscription");
        mail.setText("Cher "+userr.getNom() +",\n\n"
                + "Nous avons pris en compte votre demande d'inscription au Club Sportif La Gazelec. Après avoir examiné attentivement votre candidature, nous regrettons de vous informer que nous ne pouvons pas donner une suite favorable à votre demande d'inscription à la discipline "+ins.getDiscipline().getDiscipline()+".\n\n"
              
                + "Nous vous remercions néanmoins de l'intérêt que vous avez porté à notre club et vous encourageons à explorer d'autres opportunités sportives qui pourraient correspondre à vos attentes.\n\n"
                + "Si vous avez des questions ou souhaitez obtenir plus d'informations sur notre processus de sélection, n'hésitez pas à nous contacter. Nous serons heureux de vous fournir des éclaircissements.\n"
                + "Vous pouvez nous joindre par téléphone au 71287053 ou par e-mail à gazelecprojet@gmail.com.\n\n"
                + "Nous vous souhaitons bonne chance dans vos futurs projets sportifs et espérons que vous trouverez une activité qui vous conviendra.\n\n"
                + "Cordialement,\n"
                + "L'équipe du Club Sportif La Gazelec");
        System.out.println("Mail : " + mail);
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
	        mail.setSubject("Suppression de votre inscription à une discipline");
	        mail.setText("Cher(e) "+userr.getNom()+",\n\n"
	                + "Nous tenons à vous informer que votre inscription du Club Sportif de La Gazelec a été annulée.\n\n"
	               
	               
	                + "Si vous avez des questions ou des préoccupations concernant cette décision, nous vous encourageons à nous contacter pour en discuter davantage.\n\n"
	                + "Vous pouvez nous joindre par téléphone au 71287053 ou par e-mail à gazelecprojet@gmail.com.\n\n"
	                + "Nous vous remercions pour votre compréhension et espérons que vous trouverez une autre activité sportive qui correspondra à vos attentes.\n\n"
	                + "Cordialement,\n"
	                + "L'équipe du Club Sportif La Gazelec");
	        System.out.println("Mail : " + mail);
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
