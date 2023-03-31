package com.example.gazelec.sport.models;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name="evenement")

@JsonIgnoreProperties(ignoreUnknown=true)
public class Evénement {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	
	 private Long Id_Evénement;
	 private String titre;
	 private String lieu;
	 private String fileName ; 
	 @JsonFormat(pattern = "yyyy-MM-dd")
		private LocalDate date  ;
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
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Evénement(Long id_Evénement, String titre, String lieu, String fileName, LocalDate date,
			String description) {
		super();
		Id_Evénement = id_Evénement;
		this.titre = titre;
		this.lieu = lieu;
		this.fileName = fileName;
		this.date = date;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Evénement [Id_Evénement=" + Id_Evénement + ", titre=" + titre + ", lieu=" + lieu + ", fileName="
				+ fileName + ", date=" + date + ", description=" + description + "]";
	}
	public Evénement() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	 
}
