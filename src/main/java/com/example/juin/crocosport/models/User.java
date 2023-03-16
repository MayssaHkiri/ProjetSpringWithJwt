package com.example.juin.crocosport.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nom ;
	@NotBlank
	private String prenom ;
	@NotBlank
	@Email
	private String email;
	private String password;
	
	private String adresse ; 
	private Long telephone ; 
	
	@ManyToOne
	@JoinColumn(name="ID_ROLE", referencedColumnName="ID_Role")
	private Role role ;

	public User() {
	}

	public User(@NotBlank String nom, @NotBlank String prenom, @NotBlank @Email String email, String password,
			String adresse, Long telephone) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.adresse = adresse;
		this.telephone = telephone;
	}
	

	public User(Long id, @NotBlank String nom, @NotBlank String prenom, @NotBlank @Email String email, String password,
			String adresse, Long telephone, Role role) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.adresse = adresse;
		this.telephone = telephone;
		this.role = role;
	}

	public User(@NotBlank String nom, @NotBlank String prenom, @NotBlank @Email String email, String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
	}

	public User(@NotBlank String nom, @NotBlank String prenom, @NotBlank @Email String email, String password,
			Role role) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	

	
	
	

	
	
	
}