package com.aleixo.lbd.service;

import java.util.List;

import com.aleixo.lbd.exception.NotFoundException;
import com.aleixo.lbd.model.Job;


public interface JobService {

	public void save(Job job);

	public Job findById(Integer id) throws NotFoundException;

	public void delete(Integer id) throws NotFoundException;

	public List<Job> findAll();

	public void update(Job job);
}
