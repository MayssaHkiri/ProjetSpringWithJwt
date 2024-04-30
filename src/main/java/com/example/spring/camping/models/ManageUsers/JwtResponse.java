package com.example.spring.camping.models.ManageUsers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse implements Serializable {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String firstName;
	private String lastName ; 
	private String email;

	private String role ; 


	public JwtResponse(String token, Long id, String firstName, String lastName, String email,
			 String role) {
		super();
		this.token = token;
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;

		this.role = role;
	}




	
}