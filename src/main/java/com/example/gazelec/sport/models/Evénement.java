package com.example.gazelec.sport.models;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name="evenement")

@JsonIgnoreProperties(ignoreUnknown=true)
public class Evénement {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Long id;
	 @NotBlank
	 private String titre;
	 @NotBlank
	 private String lieu;
	 private String fileName ; 
	 @JsonFormat(pattern = "yyyy-MM-dd")
		private Date date  ;
	 @NotBlank
	 private String description;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
	
	
	public Evénement(Long id, @NotBlank String titre, @NotBlank String lieu, String fileName, Date date,
			@NotBlank String description) {
		super();
		this.id = id;
		this.titre = titre;
		this.lieu = lieu;
		this.fileName = fileName;
		this.date = date;
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Evénement [id=" + id + ", titre=" + titre + ", lieu=" + lieu + ", fileName=" + fileName + ", date="
				+ date + ", description=" + description + "]";
	}
	public Evénement() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	 
}
