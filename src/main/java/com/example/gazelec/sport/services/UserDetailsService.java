package com.example.gazelec.sport.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
    UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;
}
