package com.example.juin.crocosport.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@Column(name="ID_ROLE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;
	@JsonIgnore
	@OneToMany (mappedBy="role")
	private List<User> Users ;
	public Role() {
	}
	public Role(ERole name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ERole getName() {
		return name;
	}
	public void setName(ERole name) {
		this.name = name;
	}
}