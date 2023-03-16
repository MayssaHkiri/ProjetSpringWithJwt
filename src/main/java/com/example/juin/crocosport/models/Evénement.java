package com.example.juin.crocosport.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="evenement")
public class Evénement {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	
	 private Long Id_Evénement;
	 private String titre;
	 private String lieu;
	 private Date date;
	 private String description;
	public Long getId_Evénement() {
		return Id_Evénement;
	}
	public void setId_Evénement(Long id_Evénement) {
		Id_Evénement = id_Evénement;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Evénement(Long id_Evénement, String titre, String lieu, Date date, String description) {
		super();
		Id_Evénement = id_Evénement;
		this.titre = titre;
		this.lieu = lieu;
		this.date = date;
		this.description = description;
	}
	public Evénement() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	 
}
