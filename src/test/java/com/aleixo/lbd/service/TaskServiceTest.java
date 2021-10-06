package com.aleixo.lbd.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;

import com.aleixo.lbd.mock.ModelMock;
import com.aleixo.lbd.model.Task;

@SpringBootTest
@Transactional
@Rollback
public class TaskServiceTest {

	private static ModelMock modelMock;

	@Autowired
	private TaskService taskService;

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
		Task task = modelMock.buildTask();
		taskService.save(task);
		assertNotNull(task.getId());
	}

	@Test()
	@WithMockUser()
	public void findById() {
		Task task = modelMock.buildTask();
		taskService.save(task);
		assertNotNull(taskService.findById(task.getId()));
	}

	@Test()
	@WithMockUser()
	public void update() {
		Task task = modelMock.buildTask();
		String oldName = task.getName();
		taskService.save(task);
		assertNotNull(task.getId());
		task.setName("Task 2");
		taskService.update(task);
		assertFalse(taskService.findById(task.getId()).getName().equals(oldName));

	}
}
