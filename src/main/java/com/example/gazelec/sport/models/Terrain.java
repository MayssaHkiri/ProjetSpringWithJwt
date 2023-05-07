package com.example.gazelec.sport.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "terrain")
public class Terrain {
	@Id
	@Column(name="id_terrain")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_terrain ;
	private int num_terrain ; 
	
	public Terrain(Long id_terrain, int num_terrain, Discipline discipline, List<Reservation> reservations) {
		super();
		this.id_terrain = id_terrain;
		this.num_terrain = num_terrain;
		this.discipline = discipline;
		this.reservations = reservations;
	}

	public Terrain() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId_terrain() {
		return id_terrain;
	}

	public void setId_terrain(Long id_terrain) {
		this.id_terrain = id_terrain;
	}

	public int getNum_terrain() {
		return num_terrain;
	}

	public void setNum_terrain(int num_terrain) {
		this.num_terrain = num_terrain;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@ManyToOne
	@JoinColumn(name="Id_Discipline", referencedColumnName="Id_Discipline")
	private Discipline discipline;
	
	@JsonIgnore
	@OneToMany (mappedBy="terrain")
	private List<Reservation> reservations ;
}
