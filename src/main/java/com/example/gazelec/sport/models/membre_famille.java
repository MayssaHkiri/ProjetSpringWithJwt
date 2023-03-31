package com.example.gazelec.sport.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(name="membre_famille") 
public class membre_famille {
	@Id 
	@Column(name="id_membre_famille")
	@GeneratedValue(strategy = GenerationType.AUTO )
    private Long id ;
	
	private String nom;
	private String prenom;
	private String relation;
	
	@ManyToOne
	@JoinColumn(name="id_user", referencedColumnName="id")
	private User user ;
	 @ManyToMany
	 @JoinTable(name="inscription", joinColumns=  @JoinColumn(name="id_membre_famille"), inverseJoinColumns= @JoinColumn (name="id_discipline"))
	 private List<Discipline> disciplines;
	
}
