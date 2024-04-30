package com.example.spring.camping.models.ManageUsers;

import java.util.Collection;
import java.util.Collections;

import java.util.Objects;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {
//	private static final long serialVersionUID = 1L;
	private Long id;
	private String email;
	private String firstName;
	private String lastName ; 
	private String password;

	private GrantedAuthority authority;


	public static UserDetailsImpl build (User user ) {
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName());
		return new UserDetailsImpl(user.getId(), user.getEmail(), user.getNom() , user.getPrenom(), user.getPassword(),  authority );
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(authority);
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
