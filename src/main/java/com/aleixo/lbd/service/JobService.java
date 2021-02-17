package com.aleixo.lbd.service;

import java.util.List;

import com.aleixo.lbd.model.Job;

import javassist.NotFoundException;

public interface JobService {

	public void save(Job job);

	public void delete(Integer id) throws NotFoundException;

	public List<Job> findAll();

	public void update(Job job);
}
