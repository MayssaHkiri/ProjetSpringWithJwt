package com.example.spring.camping.controllers;

import java.util.Optional;
import java.security.SecureRandom;
import javax.validation.Valid;


import com.example.spring.camping.servicesImpl.UserServiceImpl;
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
import com.example.spring.camping.jwt.JwtUtils;
import com.example.spring.camping.models.ManageUsers.AddUserRequest;
import com.example.spring.camping.models.ERole;
import com.example.spring.camping.models.ManageUsers.JwtResponse;
import com.example.spring.camping.models.ManageUsers.LoginRequest;
import com.example.spring.camping.models.ManageUsers.MessageResponse;
import com.example.spring.camping.models.ManageUsers.Role;
import com.example.spring.camping.models.ManageUsers.SignupRequest;
import com.example.spring.camping.models.ManageUsers.User;
import com.example.spring.camping.models.ManageUsers.UserDetailsImpl;
import com.example.spring.camping.respositories.RoleRepository;
import com.example.spring.camping.respositories.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	 public final UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired 
	UserRepository utilRepo ;
	@Autowired
	UserServiceImpl userService;
	@Autowired
    private JavaMailSender javaMailSender;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
				new JwtResponse(jwt, userDetails.getId(), userDetails.getFirstName(), userDetails.getLastName(), userDetails.getEmail() , role));

	}
	//ADD ADMIN


	// fonction pour génerer un mot de passe aléatoire : 

	// add new user by the administrator 
   
   @PostMapping("/addUser")

   public ResponseEntity<?> addNewUser ( @Valid @RequestBody AddUserRequest addUserRequest )
   {
	   return  userService.addNewUser(addUserRequest);
   }




}
