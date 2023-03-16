package com.example.juin.crocosport.controllers;

import java.util.Arrays;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.juin.crocosport.jwt.JwtUtils;
import com.example.juin.crocosport.models.JwtResponse;
import com.example.juin.crocosport.models.LoginRequest;
import com.example.juin.crocosport.models.MessageResponse;
import com.example.juin.crocosport.models.Role;
import com.example.juin.crocosport.models.SignupRequest;
import com.example.juin.crocosport.models.User;
import com.example.juin.crocosport.models.UserDetailsImpl;
import com.example.juin.crocosport.respositories.RoleRepository;
import com.example.juin.crocosport.respositories.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/auth")
public class UserController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
	    Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    System.out.println("authentication -------- "+authentication);
	    String jwt = jwtUtils.generateJwtToken(authentication);
	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
	    Role role = (Role) userDetails.getAuthorities().iterator().next();

	    System.out.println("userDetails ------- "+ userDetails.toString());
	    return ResponseEntity.ok(
	            new JwtResponse(jwt, userDetails.getId(), userDetails.getFirstName(), userDetails.getEmail(), role));
	  
	}
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
	    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	        return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
	    }

	    // Create new user's account
	    User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getEmail(),
	            encoder.encode(signUpRequest.getPassword()));

	    Role userRole = signUpRequest.getRole();
	    user.setRole(userRole);
	    
	    userRepository.save(user);
	    
	    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}


}
