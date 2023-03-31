package com.example.gazelec.sport.models;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Utilisateur", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
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
	
	
	private String resetPasswordToken;
	
	@ManyToOne
	@JoinColumn(name="ID_ROLE", referencedColumnName="ID_Role")
	private Role role ;
	
	@ManyToOne
	@JoinColumn(name="Id_Discipline", referencedColumnName="Id_Discipline")
	private Discipline discipline;
	
	 @ManyToMany
	 @JoinTable(name="inscription", joinColumns=  @JoinColumn(name="id"), inverseJoinColumns= @JoinColumn (name="id_discipline"))
	 private List<Discipline> disciplines;
   
	 @JsonIgnore
		@OneToMany (mappedBy="user")
		private List<membre_famille> famille ;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, @NotBlank String nom, @NotBlank String prenom, @NotBlank @Email String email, String password,
			String adresse, Long telephone, Role role, Discipline discipline) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.adresse = adresse;
		this.telephone = telephone;
		this.role = role;
		this.discipline = discipline;
	}
	public User( @NotBlank String nom, @NotBlank String prenom, @NotBlank @Email String email, String password
			 ) {
		super();
		
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
	}
 
	// constructeur pour addUserRequest 
	public User(@NotBlank String nom, @NotBlank String prenom, @NotBlank @Email String email,
			@NotBlank String adresse,  Long telephone) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email ; 
		this.adresse = adresse;
		this.telephone = telephone;
	}
	

	public User(Long id, @NotBlank String nom, @NotBlank String prenom, @NotBlank @Email String email, String password,
			String adresse, Long telephone, String resetPasswordToken, Role role, Discipline discipline) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.adresse = adresse;
		this.telephone = telephone;
		this.resetPasswordToken = resetPasswordToken;
		this.role = role;
		this.discipline = discipline;
	}

	

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
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

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	
	
	

	
	
	
}