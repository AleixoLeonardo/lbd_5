package com.aleixo.lbd.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;

import com.aleixo.lbd.exception.NotFoundException;
import com.aleixo.lbd.mock.ModelMock;
import com.aleixo.lbd.model.Job;

@SpringBootTest
@Transactional
@Rollback
public class JobServiceTest {

	private static ModelMock modelMock;

	@Autowired
	private JobService jobService;

	private void buildModelMock() {
		if (modelMock == null) {
			modelMock = new ModelMock();
		}
	}

	@BeforeEach
	public void init() {
		buildModelMock();
	}

	@Test()
	public void save() {
		Job job = modelMock.buildJob();
		jobService.save(job);
		assertNotNull(job.getId());
	}

	@Test()
	@WithMockUser()
	public void findById() {
		Job job = modelMock.buildJob();
		jobService.save(job);
		assertNotNull(jobService.findById(job.getId()));
	}

	@Test()
	@WithMockUser()
	public void delete() {
		Job job = modelMock.buildJob();
		jobService.save(job);
		jobService.delete(job.getId());
		Assertions.assertThrows(NotFoundException.class, () -> {
			jobService.findById(job.getId());
		});
	}

	@Test()
	@WithMockUser()
	public void update() {
		Job job = modelMock.buildJob();
		String oldName = job.getName();
		jobService.save(job);
		assertNotNull(job.getId());
		job.setName("Job 2");
		jobService.update(job);
		assertFalse(jobService.findById(job.getId()).getName().equals(oldName));
		
	}
}
