package com.example.spring.camping.models.ManageUsers;


import java.sql.Date;

import javax.persistence.*;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


import com.example.spring.camping.models.ManageUsers.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "User", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    @NotBlank
    @Email
    private String email;
    private String password;

    private String adresse;
    private Long telephone;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date_naissance;


    @ManyToOne
    @JoinColumn(name = "ID_ROLE", referencedColumnName = "ID_Role")
    private Role role;


    // constructeur pour addUserRequest
    public User(@NotBlank String nom, @NotBlank String prenom, @NotBlank @Email String email,
                Long telephone, @NotBlank String encode) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.password = encode;
    }

    public User(String firstName, String lastName, String email, String adresse, Date dateNaissance, Long telephone, String encode) {
        super();
        this.nom = firstName;
        this.prenom = lastName;
        this.email = email;
        this.adresse = adresse;
        this.date_naissance = dateNaissance;
        this.telephone = telephone;
        this.password = encode;
    }
}


	
	

	
	
	
