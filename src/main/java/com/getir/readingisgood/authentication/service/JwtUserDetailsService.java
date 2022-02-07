package com.getir.readingisgood.authentication.service;

import com.getir.readingisgood.exception.ResourceNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.getir.readingisgood.authentication.config.SecurityConstants.PASSWORD;
import static com.getir.readingisgood.authentication.config.SecurityConstants.USER;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws ResourceNotFoundException {
		if (USER.equals(username)) {
			return new User(USER, PASSWORD, new ArrayList<>());
		} else {
			throw new ResourceNotFoundException("User not found with username: " + username);
		}
	}

}