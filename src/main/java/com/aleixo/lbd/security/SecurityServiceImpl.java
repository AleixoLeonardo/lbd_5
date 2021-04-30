package com.aleixo.lbd.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aleixo.lbd.constants.ValidateMessage;
import com.aleixo.lbd.model.User;
import com.aleixo.lbd.repository.UserRepository;

@Service
public class SecurityServiceImpl {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByName(username);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException(ValidateMessage.NOT_FOUND.getDescription());
		}
		return user.get();
	}
	
	public UserDetails loadUserDetailsByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByName(username);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException(ValidateMessage.NOT_FOUND.getDescription());
		}
		UserDetails userDetail =
				 org.springframework.security.core.userdetails.User.builder().username(user.get().getName())
					.password(user.get().getPassword()).authorities(user.get().getRole()).build();
		return userDetail;
	}
}
