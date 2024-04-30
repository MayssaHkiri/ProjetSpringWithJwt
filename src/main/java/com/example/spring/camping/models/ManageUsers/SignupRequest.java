package com.example.spring.camping.models.ManageUsers;



import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
@Getter
@Setter
public class SignupRequest {
	@NotBlank
	@Size(min = 3, max = 20)
	private String firstName;

	@NotBlank
	@Size(min = 3, max = 20)
	private String lastName;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	private Long telephone ;

	@NotBlank
	@Size(min = 5, max = 40)
	private String password;
	private String role;



}