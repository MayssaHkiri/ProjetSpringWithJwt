package com.example.gazelec.sport.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "inscription")
public class Inscription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_inscription;
	
	
		private String mode_paiement;


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


		public Inscription(Long id_inscription, String mode_paiement) {
			super();
			this.id_inscription = id_inscription;
			this.mode_paiement = mode_paiement;
		}


		public Inscription() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		

		
}
