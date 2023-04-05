package com.example.gazelec.sport.models;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "inscription")
public class Inscription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_inscription;
	
	   @JsonFormat(pattern = "yyyy-MM-dd")
	    private LocalDateTime date_inscription  ;
		private String mode_paiement;
		private String status;
		
		@ManyToOne
		@JoinColumn(name="id_discipline", referencedColumnName="Id_Discipline")
		private Discipline discipline ;
		
		@ManyToOne
		@JoinColumn(name="id_user", referencedColumnName="id")
		private User user ;
		
		@ManyToOne
		@JoinColumn(name="id_membre_famille", referencedColumnName="id_membre_famille")
		private membre_famille membre_famille ;
		


		public Long getId_inscription() {
			return id_inscription;
		}


		public void setId_inscription(Long id_inscription) {
			this.id_inscription = id_inscription;
		}


		public String getMode_paiement() {
			return mode_paiement;
		}


		public void setMode_paiement(String mode_paiement) {
			this.mode_paiement = mode_paiement;
		}


		

		public Inscription() {
			super();
			// TODO Auto-generated constructor stub
		}


		


		public Discipline getDiscipline() {
			return discipline;
		}


		public void setDiscipline(Discipline discipline) {
			this.discipline = discipline;
		}


		public User getUser() {
			return user;
		}


		public void setUser(User user) {
			this.user = user;
		}


		public membre_famille getMembre_famille() {
			return membre_famille;
		}


		public void setMembre_famille(membre_famille membre_famille) {
			this.membre_famille = membre_famille;
		}


		public LocalDateTime getDate_inscription() {
			return date_inscription;
		}


		public void setDate_inscription(LocalDateTime localDateTime) {
			this.date_inscription = localDateTime;
		}


		

		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}


		public Inscription(Long id_inscription, LocalDateTime date_inscription, String mode_paiement, String status,
				Discipline discipline, User user, com.example.gazelec.sport.models.membre_famille membre_famille) {
			super();
			this.id_inscription = id_inscription;
			this.date_inscription = date_inscription;
			this.mode_paiement = mode_paiement;
			this.status = status;
			this.discipline = discipline;
			this.user = user;
			this.membre_famille = membre_famille;
		}


	
		
		

		
}
