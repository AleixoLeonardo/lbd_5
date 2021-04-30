package com.aleixo.lbd.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleixo.lbd.constants.ValidateMessage;
import com.aleixo.lbd.exception.NotFoundException;
import com.aleixo.lbd.model.Task;
import com.aleixo.lbd.model.TaskMtmJob;
import com.aleixo.lbd.repository.TaskRepository;
import com.aleixo.lbd.service.TaskMtmJobService;

@Service
public class TaskMtmJobServiceImpl implements TaskMtmJobService{

	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public List<TaskMtmJob> findByTask(Integer idTask) {
		Optional<Task> taskOpt = taskRepository.findById(idTask);
		if (!taskOpt.isPresent()) {
			throw new NotFoundException(ValidateMessage.NOT_FOUND.getDescription());
		}
		return taskOpt.get().getTaskMtmJobList();
	}

}
