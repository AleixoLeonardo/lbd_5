package com.aleixo.lbd.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.aleixo.lbd.constants.ValidateMessage;
import com.aleixo.lbd.exception.NotFoundException;
import com.aleixo.lbd.model.Task;
import com.aleixo.lbd.repository.TaskRepository;
import com.aleixo.lbd.service.HistoryTaskService;
import com.aleixo.lbd.service.TaskService;
import com.aleixo.lbd.service.validator.TaskValidator;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskValidator taskValidator;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private HistoryTaskService historyTaskService;
	
	@Override
	public Integer save(Task task) {
		try {			
			taskValidator.validateData(task, false);
			
			return taskRepository.save(task).getId();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	@Override
	@PreAuthorize("isAuthenticated()")
	public void delete(Integer id) throws NotFoundException {
		Optional<Task> task = taskRepository.findById(id);
		if (task.isPresent()) {
			historyTaskService.delete(task.get().getHistoryTaskList());
			taskRepository.delete(task.get());
			return;
		}
		throw new NotFoundException(ValidateMessage.NOT_FOUND.getDescription());
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Task> findAll() {
		return taskRepository.findAllTasks();
	}

	@Override
	public void update(Task task) {
		try {			
			taskValidator.validateData(task, false);
			
			taskRepository.save(task);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public Task findById(Integer id) throws NotFoundException {
		Optional<Task> task = taskRepository.findById(id);
		if (task.isPresent()) {
			return task.get();
		}
		throw new NotFoundException(ValidateMessage.NOT_FOUND.getDescription());
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Task> findByJob(Integer id) throws NotFoundException {
		return taskRepository.findAllByJob(id);
	}

}
