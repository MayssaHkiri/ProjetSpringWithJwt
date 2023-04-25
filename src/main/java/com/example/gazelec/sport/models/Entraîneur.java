package com.example.gazelec.sport.models;





import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="Entraîneur")
public class Entraîneur {
	@Id 
	@Column(name="Id_Entraîneur")
	@GeneratedValue(strategy = GenerationType.AUTO )
    private Long id ; 
	@NotBlank
	private String nom;
	@NotBlank
	private String prenom;
	@NotBlank
	@Email
	private String email;
	@NotBlank 
	private String adresse ; 
	@NotBlank
	private String telephone;

	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date naissance   ;
	

    @Column(name = "date_embauche")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateEmbauche;

	private int experience;
	@JsonIgnore
	@ManyToOne
	 @JsonBackReference
	@JoinColumn(name="Id_Discipline", referencedColumnName="Id_Discipline")
	private Discipline discipline ;
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
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Discipline getDiscipline() {
		return discipline;
	}
	
	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}
	
	
	
	public Entraîneur(@NotBlank String nom, @NotBlank String prenom, @NotBlank @Email String email,
			@NotBlank String adresse, @NotBlank String telephone, Date naissance) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.telephone = telephone;
		this.naissance = naissance;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public Entraîneur(Long id, @NotBlank String nom, @NotBlank String prenom, @NotBlank @Email String email,
			@NotBlank String telephone, Date naissance) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.naissance = naissance;
	}
	public Entraîneur(@NotBlank String nom, @NotBlank String prenom, @NotBlank @Email String email,
			@NotBlank String telephone, Date naissance, Discipline discipline) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.naissance = naissance;
		this.discipline = discipline;
	}
	public Date getNaissance() {
		return naissance;
	}
	public void setNaissance(Date naissance) {
		this.naissance = naissance;
	}
	
	public Entraîneur() {
		super();
		
	}

	public LocalDate getDateEmbauche() {
		return dateEmbauche;
	}
	public void setDateEmbauche(LocalDate dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}

}
