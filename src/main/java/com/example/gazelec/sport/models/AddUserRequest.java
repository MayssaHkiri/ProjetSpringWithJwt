package com.example.gazelec.sport.models;
import javax.validation.constraints.*;
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
	
	private Long telephone ; 
	
	private String role ; 
	
	private Long id_discipline ;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Long getTelephone() {
		return telephone;
	}

	public void setTelephone(Long telephone) {
		this.telephone = telephone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getId_discipline() {
		return id_discipline;
	}

	public void setId_discipline(Long id_discipline) {
		this.id_discipline = id_discipline;
	} 
	
	
}
