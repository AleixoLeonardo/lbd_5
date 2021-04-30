package com.aleixo.lbd.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aleixo.lbd.constants.ValidateMessage;
import com.aleixo.lbd.model.User;
import com.aleixo.lbd.repository.UserRepository;
import com.aleixo.lbd.service.HistoryTaskService;
import com.aleixo.lbd.service.UserService;
import com.aleixo.lbd.service.validator.UserValidator;

import javassist.NotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private HistoryTaskService historyTaskService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public void save(User user) {
		userValidator.validateData(user, false);
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void delete(Integer id) throws NotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			historyTaskService.deleteAllByUser(id);
			userRepository.delete(user.get());
			return;
		}
		throw new NotFoundException(ValidateMessage.NOT_FOUND.getDescription());
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> findAll() {
		return userRepository.findAllUsers();
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void update(User user) {
		userValidator.validateData(user, true);
		userRepository.save(user);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public User findById(Integer id) throws NotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		throw new NotFoundException(ValidateMessage.NOT_FOUND.getDescription());
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public User findByName(String name) throws NotFoundException {
		Optional<User> user = userRepository.findByName(name);
		if (user.isPresent()) {
			return user.get();
		}
		throw new NotFoundException(ValidateMessage.NOT_FOUND.getDescription());
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public UserDetails findByNameAuthenticate(String name) throws NotFoundException {
		Optional<User> user = userRepository.findByName(name);
		if (user.isPresent()) {

			return new org.springframework.security.core.userdetails.User(user.get().getName(),
					user.get().getPassword(), new ArrayList<>());
		}
		throw new NotFoundException(ValidateMessage.NOT_FOUND.getDescription());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
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
