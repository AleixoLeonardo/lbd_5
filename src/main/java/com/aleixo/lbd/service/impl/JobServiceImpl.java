package com.aleixo.lbd.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleixo.lbd.constants.ValidateMessage;
import com.aleixo.lbd.model.Job;
import com.aleixo.lbd.repository.JobRepository;
import com.aleixo.lbd.service.JobService;
import com.aleixo.lbd.service.validator.JobValidator;

import javassist.NotFoundException;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobValidator jobValidator;

	@Autowired
	private JobRepository jobRepository;

	@Override
	public void save(Job job) {
		jobValidator.validateData(job, false);
		jobRepository.save(job);
	}

	@Override
	public void delete(Integer id) throws NotFoundException {
		Optional<Job> job = jobRepository.findById(id);
		if (job.isPresent()) {
			jobRepository.delete(job.get());
			return;
		}
		throw new NotFoundException(ValidateMessage.NOT_FOUND.getDescription());
	}

	@Override
	public List<Job> findAll() {
		return jobRepository.findAllJobs();
	}

	@Override
	public void update(Job job) {
		jobValidator.validateData(job, false);
		jobRepository.save(job);
	}

}
