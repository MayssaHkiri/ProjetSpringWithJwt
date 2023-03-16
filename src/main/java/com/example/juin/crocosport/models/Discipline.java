package com.example.juin.crocosport.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table(name="Discipline") 
public class Discipline {
	@Id 
	@Column(name="Id_Discipline")
	@GeneratedValue(strategy = GenerationType.AUTO )
    private Long id ; 
	private String discipline;
	@OneToMany (mappedBy="discipline")
	 private List<Entraîneur> entraineurs ;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDiscipline() {
		return discipline;
	}
	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}
		
	public List<Entraîneur> getEntraineurs() {
		return entraineurs;
	}
	public void setEntraineurs(List<Entraîneur> entraineurs) {
		this.entraineurs = entraineurs;
	}
	public Discipline(Long id, String discipline, List<Entraîneur> entraineurs) {
		super();
		this.id = id;
		this.discipline = discipline;
		this.entraineurs = entraineurs;
	}
	public Discipline() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
