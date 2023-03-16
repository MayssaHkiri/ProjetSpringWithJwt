package com.example.juin.crocosport.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Entraîneur")
public class Entraîneur {
	@Id 
	@Column(name="Id_Entraîneur")
	@GeneratedValue(strategy = GenerationType.AUTO )
    private Long id ; 
	private String nom;
	private String prénom;
	private String email;
	private String téléphone;
	private Date Date_De_Naissance;
	@JsonIgnore
	@ManyToOne
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
	public String getPrénom() {
		return prénom;
	}
	public void setPrénom(String prénom) {
		this.prénom = prénom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTéléphone() {
		return téléphone;
	}
	public void setTéléphone(String téléphone) {
		this.téléphone = téléphone;
	}
	public Date getDate_De_Naissance() {
		return Date_De_Naissance;
	}
	public void setDate_De_Naissance(Date date_De_Naissance) {
		Date_De_Naissance = date_De_Naissance;
	}
	public Discipline getDiscipline() {
		return discipline;
	}
	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}
	public Entraîneur(Long id, String nom, String prénom, String email, String téléphone, Date date_De_Naissance,
			Discipline discipline) {
		super();
		this.id = id;
		this.nom = nom;
		this.prénom = prénom;
		this.email = email;
		this.téléphone = téléphone;
		Date_De_Naissance = date_De_Naissance;
		this.discipline = discipline;
	}
	public Entraîneur() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
