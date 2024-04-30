package com.example.spring.camping.models.ManageUsers;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.sql.Date;

@Getter
@Setter
public class AddUserRequest {
	@NotBlank
	@Size(min = 3, max = 20)
	private String firstName ; 
	@NotBlank
	@Size(min = 3, max = 20)
	private String lastName ; 
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	@NotBlank
	@Size(min =3 , max = 100)
	private String adresse ;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date_naissance  ;

	private Long telephone ; 
	
	private String role ;

	@Size(min = 5, max = 40)
	private String password;


	
	
}
