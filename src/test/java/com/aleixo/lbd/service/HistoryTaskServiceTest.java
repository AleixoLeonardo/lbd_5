package com.aleixo.lbd.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

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
import com.aleixo.lbd.model.HistoryTask;
import com.aleixo.lbd.model.Job;
import com.aleixo.lbd.model.Task;
import com.aleixo.lbd.model.User;

@SpringBootTest
@Transactional
@Rollback
public class HistoryTaskServiceTest {

	private static ModelMock modelMock;

	@Autowired
	private HistoryTaskService historyTaskService;

	@Autowired
	private UserService userService;

	@Autowired
	private TaskService taskService;

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

	private User getUserSaved(Job job) {
		User user = modelMock.buildUser(getJobSaved());
		userService.save(user);
		return user;
	}

	private Task getTaskSaved() {
		Task task = modelMock.buildTask();
		taskService.save(task);
		return task;
	}

	private Job getJobSaved() {
		Job job = modelMock.buildJob();
		jobService.save(job);
		return job;
	}

	@Test()
	public void save() {

		try {

			HistoryTask historyTask = modelMock.buildHistoryTask(getUserSaved(getJobSaved()), getTaskSaved());
			historyTaskService.save(historyTask);
			assertNotNull(historyTask.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test()
	@WithMockUser()
	public void findById() {

		HistoryTask historyTask = modelMock.buildHistoryTask(getUserSaved(getJobSaved()), getTaskSaved());
		historyTaskService.save(historyTask);
		assertNotNull(historyTaskService.findById(historyTask.getId()));
	}

	@Test()
	@WithMockUser()
	public void findByUser() {

		User user = getUserSaved(getJobSaved());
		HistoryTask historyTask = modelMock.buildHistoryTask(user, getTaskSaved());
		historyTaskService.save(historyTask);
		List<HistoryTask> historyTaskList = historyTaskService.findByUser(user.getId());
		assertFalse(historyTaskList.isEmpty());
	}

	@Test()
	@WithMockUser(username = "Leonardo", password = "Aleixo@123", roles = { "ADMIN" })
	public void findByIdTaskPeriod() {

		Long start = modelMock.getFirstDayOfMonth().getTime();
		Long end = modelMock.getEndOfMonth().getTime();
		User user = getUserSaved(getJobSaved());
		Task task = getTaskSaved();
		HistoryTask historyTask = modelMock.buildHistoryTask(user, task);
		historyTaskService.save(historyTask);
		List<HistoryTask> listHistoryTasks = historyTaskService.findByIdTaskPeriod(task.getId(), start, end);
		assertFalse(listHistoryTasks.isEmpty());
	}

	@Test()
	@WithMockUser()
	public void delete() {

		User user = getUserSaved(getJobSaved());
		Task task = getTaskSaved();
		HistoryTask historyTask = modelMock.buildHistoryTask(user, task);
		historyTaskService.save(historyTask);
		historyTaskService.delete(historyTask.getId());
		Assertions.assertThrows(NotFoundException.class, () -> {
			historyTaskService.findById(historyTask.getId());
		});
	}
}
