package com.aleixo.lbd.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.aleixo.lbd.model.User;

import javassist.NotFoundException;

public interface UserService extends UserDetailsService {
	public void save(User user);

	public User findById(Integer id) throws NotFoundException;

	public User findByName(String name) throws NotFoundException;

	public UserDetails findByNameAuthenticate(String name) throws NotFoundException;

	public void delete(Integer id) throws NotFoundException;

	public List<User> findAll();

	public void update(User user);
}
