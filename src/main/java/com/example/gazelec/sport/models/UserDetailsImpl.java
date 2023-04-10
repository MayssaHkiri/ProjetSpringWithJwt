package com.example.gazelec.sport.models;

import java.util.Collection;
import java.util.Collections;

import java.util.Objects;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
//	private static final long serialVersionUID = 1L;
	private Long id;
	private String email;
	private String firstName;
	private String lastName ; 
	private String password;
	private Discipline discipline ; 
	private GrantedAuthority authority;

	/*public UserDetailsImpl(Long id, String email, String firstName , String lastName , String password,
			GrantedAuthority authority) {
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName ; 
		this.password = password;
		this.authority = authority;
	}*/
	
     
	
	public static UserDetailsImpl build (User user ) {
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName().name()); 
		return new UserDetailsImpl(user.getId(), user.getEmail(), user.getNom() , user.getPrenom(), user.getPassword(), user.getDiscipline(), authority ); 
	}

	public UserDetailsImpl(Long id, String email, String firstName, String lastName, String password,
			Discipline discipline, GrantedAuthority authority) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.discipline = discipline;
		this.authority = authority;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(authority);
	}
    
	public Discipline getDiscipline() {
		return discipline;
	}


	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	public String getFirstName() {
		return firstName;
	}
    
	public String getLastName() {
		return lastName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public String toString() {
		return "UserDetailsImpl [id=" + id + ", email=" + email + ", firstName=" + firstName + ", password=" + password
				+ ", authority=" + authority + "]";
	}

	

}
