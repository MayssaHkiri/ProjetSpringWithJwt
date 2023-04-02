package com.example.gazelec.sport.models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity 
@Table(name="membre_famille") 
public class membre_famille {
	@Id 
	@Column(name="id_membre_famille")
	@GeneratedValue(strategy = GenerationType.AUTO )
    private Long id ;

	private String nom;
	private String prenom;
	private String email;
	private String relation;
	private String adresse ; 
	private Long telephone ;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date_naissance  ;
	    private String lieu_naissance;
	  
	
	
	@ManyToOne
	@JoinColumn(name="id_user", referencedColumnName="id")
	private User user ;
	 @ManyToMany
	 @JoinTable(name="inscription", joinColumns=  @JoinColumn(name="id_membre_famille"), inverseJoinColumns= @JoinColumn (name="id_discipline"))
	 private List<Discipline> disciplines;
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
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Discipline> getDisciplines() {
		return disciplines;
	}
	public void setDisciplines(List<Discipline> disciplines) {
		this.disciplines = disciplines;
	}
	public membre_famille() {
		super();
		// TODO Auto-generated constructor stub
	}
	public membre_famille(Long id, String nom, String prenom, String relation, User user,
			List<Discipline> disciplines) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.relation = relation;
		this.user = user;
		this.disciplines = disciplines;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public membre_famille(Long id, String nom, String prenom, String email, String relation, User user,
			List<Discipline> disciplines) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.relation = relation;
		this.user = user;
		this.disciplines = disciplines;
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
	public Date getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}
	public String getLieu_naissance() {
		return lieu_naissance;
	}
	public void setLieu_naissance(String lieu_naissance) {
		this.lieu_naissance = lieu_naissance;
	}
	public membre_famille(Long id, String nom, String prenom, String email, String relation, String adresse,
			Long telephone, Date date_naissance, String lieu_naissance, User user, List<Discipline> disciplines) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.relation = relation;
		this.adresse = adresse;
		this.telephone = telephone;
		this.date_naissance = date_naissance;
		this.lieu_naissance = lieu_naissance;
		this.user = user;
		this.disciplines = disciplines;
	}
	
}
