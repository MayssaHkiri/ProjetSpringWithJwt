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
	private String adresse ; 
	private Long telephone ;
	@JsonFormat(pattern = "yyyy-MM-dd")
		private LocalDate Date_De_Naissance  ;
		private String cin;
		private String profession;
		private String lieu_naissance;
		
		@Lob
		private byte[] certificatMedical;

		public Long getId_inscription() {
			return id_inscription;
		}

		public void setId_inscription(Long id_inscription) {
			this.id_inscription = id_inscription;
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

		public LocalDate getDate_De_Naissance() {
			return Date_De_Naissance;
		}

		public void setDate_De_Naissance(LocalDate date_De_Naissance) {
			Date_De_Naissance = date_De_Naissance;
		}

		public String getCin() {
			return cin;
		}

		public void setCin(String cin) {
			this.cin = cin;
		}

		public String getProfession() {
			return profession;
		}

		public void setProfession(String profession) {
			this.profession = profession;
		}

		public String getLieu_naissance() {
			return lieu_naissance;
		}

		public void setLieu_naissance(String lieu_naissance) {
			this.lieu_naissance = lieu_naissance;
		}

		public byte[] getCertificatMedical() {
			return certificatMedical;
		}

		public void setCertificatMedical(byte[] certificatMedical) {
			this.certificatMedical = certificatMedical;
		}

		public Inscription(Long id_inscription, String adresse, Long telephone, LocalDate date_De_Naissance, String cin,
				String profession, String lieu_naissance, byte[] certificatMedical) {
			super();
			this.id_inscription = id_inscription;
			this.adresse = adresse;
			this.telephone = telephone;
			Date_De_Naissance = date_De_Naissance;
			this.cin = cin;
			this.profession = profession;
			this.lieu_naissance = lieu_naissance;
			this.certificatMedical = certificatMedical;
		}

		public Inscription() {
			super();
			// TODO Auto-generated constructor stub
		}

}
