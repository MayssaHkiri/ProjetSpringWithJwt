package com.example.spring.camping.models.ManageUsers;

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

import com.example.spring.camping.models.ERole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "role")
public class Role {
	@Id
	@Column(name="id_role")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;


	private String name;
	@JsonIgnore
	@OneToMany (mappedBy="role")
	private List<User> Users ;



}