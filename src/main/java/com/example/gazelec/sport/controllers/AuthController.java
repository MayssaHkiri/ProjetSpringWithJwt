package com.example.gazelec.sport.controllers;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

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

import com.example.gazelec.sport.jwt.JwtUtils;
import com.example.gazelec.sport.models.ERole;
import com.example.gazelec.sport.models.JwtResponse;
import com.example.gazelec.sport.models.LoginRequest;
import com.example.gazelec.sport.models.MessageResponse;
import com.example.gazelec.sport.models.Role;
import com.example.gazelec.sport.models.SignupRequest;
import com.example.gazelec.sport.models.User;
import com.example.gazelec.sport.models.UserDetailsImpl;
import com.example.gazelec.sport.respositories.RoleRepository;
import com.example.gazelec.sport.respositories.UserRepository;



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
	   // String  role =  userDetails.getAuthorities().iterator().next();
	    String role = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .findFirst()
                .orElse(null);
	    System.out.println("userDetails ------- "+ userDetails.toString());
	    return ResponseEntity.ok(
	            new JwtResponse(jwt, userDetails.getId(), userDetails.getFirstName(), userDetails.getLastName(), userDetails.getEmail(), role));
	  
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
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			user.setRole(userRole); 
		} else {
		
				switch (strRole) {
				case "admin":
					
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					user.setRole(adminRole); 
					System.out.println("here role admin" + strRole);
					break;
				default:
					System.out.println("here role user" + strRole);
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					user.setRole(userRole); 
				}
			;
		}
	    
		userRepository.save(user);
	    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	


}
