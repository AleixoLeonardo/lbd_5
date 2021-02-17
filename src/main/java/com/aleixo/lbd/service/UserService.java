package com.aleixo.lbd.service;

import java.util.List;

import com.aleixo.lbd.model.User;

import javassist.NotFoundException;

public interface UserService {
	public void save(User user);
	
	public User findById(Integer id) throws NotFoundException;
	
	public void delete(Integer id) throws NotFoundException;
	
	public List<User> findAll();
	
	public void update(User user);
}
