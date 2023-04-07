package com.example.gazelec.sport.controllers;

import java.util.Arrays;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.Collections;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.security.SecureRandom;
import javax.validation.Valid;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable ; 
import com.example.gazelec.sport.jwt.JwtUtils;
import com.example.gazelec.sport.models.AddUserRequest;
import com.example.gazelec.sport.models.Discipline;
import com.example.gazelec.sport.models.ERole;
import com.example.gazelec.sport.models.JwtResponse;
import com.example.gazelec.sport.models.LoginRequest;
import com.example.gazelec.sport.models.MessageResponse;
import com.example.gazelec.sport.models.Role;
import com.example.gazelec.sport.models.SignupRequest;
import com.example.gazelec.sport.models.User;
import com.example.gazelec.sport.models.UserDetailsImpl;
import com.example.gazelec.sport.respositories.DisciplineRepository;
import com.example.gazelec.sport.respositories.RoleRepository;
import com.example.gazelec.sport.respositories.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	DisciplineRepository disRepo; 
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired 
	UserRepository utilRepo ; 
	@Autowired
    private JavaMailSender javaMailSender;
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
	    Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    System.out.println("authentication -------- "+authentication);
	    String jwt = jwtUtils.generateJwtToken(authentication);
	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
	   // String  role =  userDetails.getAuthorities().iterator().next();
	    String role = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .findFirst()
                .orElse(null);
	    System.out.println("userDetails ------- "+ userDetails.toString());
	    return ResponseEntity.ok(
	            new JwtResponse(jwt, userDetails.getId(), userDetails.getFirstName(), userDetails.getLastName(), userDetails.getEmail() , userDetails.getDiscipline(), role));
	  
	}
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
	    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	        return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
	    }

	    // Create new user's account
	    User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getEmail(),
	            encoder.encode(signUpRequest.getPassword()));
	    String strRole = signUpRequest.getRole();
        
	    /*Role userRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	    user.setRole(userRole);
	   
	    userRepository.save(user);*/
	    if (strRole == null) {
			Role userRole = roleRepository.findByName(ERole.ADHERENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			user.setRole(userRole); 
		} else {
		
				switch (strRole) {
				case "admin":
					
					Role adminRole = roleRepository.findByName(ERole.ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					user.setRole(adminRole); 
					System.out.println("here role admin" + strRole);
					break;
				default:
					System.out.println("here role user" + strRole);
					Role userRole = roleRepository.findByName(ERole.ADHERENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					user.setRole(userRole); 
				}
			;
		}
	    
		userRepository.save(user);
	    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	// Update profile 
	@PutMapping("/UpdateCurrentUser")
	public User UpdateProfile (@RequestBody User user) {
	    Optional<User> optionalUser = utilRepo.findById(user.getId()); 
	    if (optionalUser.isPresent()) { 
	        User existingUser = optionalUser.get();
	        existingUser.setNom(user.getNom()); 
	        existingUser.setPrenom(user.getPrenom()); 
	        existingUser.setEmail(user.getEmail());
	        existingUser.setTelephone(user.getTelephone());
	        existingUser.setAdresse(user.getAdresse());
	        existingUser.setDate_naissance(user.getDate_naissance());
	        existingUser.setLieu_naissance(user.getLieu_naissance());
	        existingUser.setProfession(user.getProfession());
	        existingUser.setMatricule(user.getMatricule());
	        existingUser.setStegiste(user.getStegiste());
	        
	        // Autres champs que vous souhaitez mettre à jour

	        return utilRepo.save(existingUser);
	    }
	    return null; // ou une réponse appropriée si l'utilisateur n'a pas été trouvé
	}
	
	// Get current user 
	@GetMapping("/{id}")
	public User getCurrentUserById (@PathVariable Long id )
	{
		Optional<User> U = utilRepo.findById(id); 
		return U.isPresent() ? U.get() : null;
	}

	// fonction pour génerer un mot de passe aléatoire : 
	public static String generatePassword(int length) {
	    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    SecureRandom random = new SecureRandom();
	    StringBuilder sb = new StringBuilder(length);

	    for (int i = 0; i < length; i++) {
	        int randomIndex = random.nextInt(characters.length());
	        sb.append(characters.charAt(randomIndex));
	    }

	    return sb.toString();
	}
	// add new user by the administrator 
   
   @PostMapping("/addUser")
  // @PreAuthorize("hasRole('ROLE_MODERATEUR')")
   public ResponseEntity<?> addNewUser ( @Valid @RequestBody AddUserRequest addUserRequest )
   {
	  
	   if (userRepository.existsByEmail(addUserRequest.getEmail())) {
		   return ResponseEntity.badRequest().body(new MessageResponse("Error : Email is already taken ! ")) ; 
	   }
	   
	   // add new user 
	   User user = new User (addUserRequest.getFirstName() , addUserRequest.getLastName() , 
			   addUserRequest.getEmail() , addUserRequest.getAdresse(), addUserRequest.getTelephone());  
	   
	   String strRole = addUserRequest.getRole(); 
	   
	   // affecter un role à l'utilisateur 
	   if (strRole == null) {
		   return ResponseEntity.badRequest().body(new MessageResponse("Error : Role non connu  ! ")) ; 
	   }
	   else {
		   switch (strRole) {
			case "moderateur":
				
				Role RoleModerateur = roleRepository.findByName(ERole.MODERATEUR)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				user.setRole(RoleModerateur); 
				//affecter une discipline 
				
				 Optional<Discipline> D = disRepo.findById(addUserRequest.getId_discipline()) ; 	
				
				 if (!D.isPresent()) {
					System.out.println("discipline n'existe pas ! ");
					return ResponseEntity.badRequest().body(new MessageResponse("Error : la discipline ne se trouve pas ! ")) ; 
				 }
				 else {
					 Discipline dis = D.get(); 
					 user.setDiscipline(dis); 
				 }
				
				System.out.println("here role moderateur " + strRole);
				break;
			default:
				System.out.println("here role user" + strRole);
				Role RoleGestionnaire  = roleRepository.findByName(ERole.GESTIONNAIRE)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				user.setRole(RoleGestionnaire); 
			}
		;
		// l'appel de la methode generatePassword 
		String password = generatePassword(8); 
		user.setPassword(encoder.encode(password));
		// l'envoie du mail contenant le password 
		
	        // Send password reset email
	        SimpleMailMessage mail = new SimpleMailMessage();
	        mail.setFrom("gazelecprojet@gmail.com");
	        mail.setTo(addUserRequest.getEmail());
	        mail.setSubject("Nouveau mot de passe ");
	        mail.setText("Vous etes un nouveau utilisateur chez Gazelec.tn  \n vous trouvez votre mot de passe: \n " + password  );
	        System.out.println("Mail "+mail);
	        javaMailSender.send(mail);
	       
		   
	   }
	   
	    userRepository.save(user);
	    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	     
   }
   @GetMapping("/ExistEmail/{email}")
   public boolean existmail (@PathVariable String email )
   {
	    boolean exist=false;
	    if(userRepository.existsByEmail(email))
	    {exist=true;}
	    
	    return exist;
   }

}
