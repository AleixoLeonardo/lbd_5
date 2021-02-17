package com.aleixo.lbd.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleixo.lbd.constants.ValidateMessage;
import com.aleixo.lbd.exception.NotFoundException;
import com.aleixo.lbd.model.Task;
import com.aleixo.lbd.repository.TaskRepository;
import com.aleixo.lbd.service.TaskService;
import com.aleixo.lbd.service.validator.TaskValidator;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskValidator taskValidator;

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public void save(Task task) {
		taskValidator.validateData(task, false);
		taskRepository.save(task);
	}

	@Override
	public void delete(Integer id) throws NotFoundException {
		Optional<Task> task = taskRepository.findById(id);
		if (task.isPresent()) {
			taskRepository.delete(task.get());
			return;
		}
		throw new NotFoundException(ValidateMessage.NOT_FOUND.getDescription());
	}

	@Override
	public List<Task> findAll() {
		return taskRepository.findAllTasks();
	}

	@Override
	public void update(Task task) {
		taskValidator.validateData(task, false);
		taskRepository.save(task);
	}

	@Override
	public Task findById(Integer id) throws NotFoundException {
		Optional<Task> task = taskRepository.findById(id);
		if (task.isPresent()) {
			return task.get();
		}
		throw new NotFoundException(ValidateMessage.NOT_FOUND.getDescription());
	}

}
