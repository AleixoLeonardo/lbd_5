package com.aleixo.lbd.service;

import java.util.List;

import com.aleixo.lbd.model.Task;

import javassist.NotFoundException;

public interface TaskService {
	public void save(Task task);

	public Task findById(Integer id) throws com.aleixo.lbd.exception.NotFoundException;

	public void delete(Integer id) throws NotFoundException;

	public List<Task> findAll();

	public void update(Task task);
}
