package com.example.gazelec.sport.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {
	@Id
	@Column(name="id_reservation")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_reservation ; 
	private Date date ; 
	private String Hdebut ;
	private String Hfin ; 
	private String status ; 
	
	public Long getId_reservation() {
		return id_reservation;
	}

	public void setId_reservation(Long id_reservation) {
		this.id_reservation = id_reservation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHdebut() {
		return Hdebut;
	}

	public void setHdebut(String hdebut) {
		Hdebut = hdebut;
	}

	public String getHfin() {
		return Hfin;
	}

	public void setHfin(String hfin) {
		Hfin = hfin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Reservation(Long id_reservation, Date date, String hdebut, String hfin, String status, Terrain terrain,
			User user) {
		super();
		this.id_reservation = id_reservation;
		this.date = date;
		Hdebut = hdebut;
		Hfin = hfin;
		this.status = status;
		this.terrain = terrain;
		this.user = user;
	}

	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	@ManyToOne
	@JoinColumn(name="id_terrain", referencedColumnName="id_terrain")
	private Terrain terrain  ;
	
	@ManyToOne
	@JoinColumn(name="id_user", referencedColumnName="id")
	private User user ;
	
}
