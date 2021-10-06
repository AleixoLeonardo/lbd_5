package com.aleixo.lbd.mock;

import java.util.Date;

import org.joda.time.DateTime;

import com.aleixo.lbd.model.HistoryTask;
import com.aleixo.lbd.model.Job;
import com.aleixo.lbd.model.Task;
import com.aleixo.lbd.model.User;

public class ModelMock {

	private static String ROLE_ADMIN_MOCK = "ADMIN";
	private static String JOB_NAME_MOCK = "Job mock";
	private static String TASK_NAME_MOCK = "Task mock";
	private static String PASS_USER_MOCK = "123";

	public User buildUser(Job job) {
		User user = new User();
		user.setBirthDate(new Date());
		user.setCpf("11111111111");
		user.setJobId(job);
		user.setName("Nome teste");
		user.setPassword(PASS_USER_MOCK);
		user.setRole(ROLE_ADMIN_MOCK);
		return user;
	}

	public Job buildJob() {
		Job job = new Job();
		job.setName(JOB_NAME_MOCK);
		return job;
	}

	public HistoryTask buildHistoryTask(User user, Task task) {
		HistoryTask historyTask = new HistoryTask();
		historyTask.setHistoryDate(new Date());
		historyTask.setTaskId(task);
		historyTask.setUserId(user);
		return historyTask;
	}

	public Task buildTask() {
		Task task = new Task();
		task.setName(TASK_NAME_MOCK);
		return task;
	}

	public Date getEndOfMonth() {
		return new DateTime().dayOfMonth().withMaximumValue().toDate();
	}

	public Date getFirstDayOfMonth() {
		return new DateTime().dayOfMonth().withMinimumValue().toDate();
	}

}
