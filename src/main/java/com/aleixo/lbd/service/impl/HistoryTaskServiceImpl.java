package com.aleixo.lbd.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleixo.lbd.constants.ValidateMessage;
import com.aleixo.lbd.model.HistoryTask;
import com.aleixo.lbd.repository.HistoryTaskRepository;
import com.aleixo.lbd.service.HistoryTaskService;
import com.aleixo.lbd.service.validator.HistoryTaskValidator;

import javassist.NotFoundException;

@Service
public class HistoryTaskServiceImpl implements HistoryTaskService {

	@Autowired
	private HistoryTaskValidator historyTaskValidator;

	@Autowired
	private HistoryTaskRepository historyTaskRepository;

	@Override
	public void save(HistoryTask historyTask) {
		historyTaskValidator.validateData(historyTask, false);
		historyTaskRepository.save(historyTask);
	}

	@Override
	public void delete(Integer id) throws NotFoundException {
		Optional<HistoryTask> historyTask = historyTaskRepository.findById(id);
		if (historyTask.isPresent()) {
			historyTaskRepository.delete(historyTask.get());
			return;
		}
		throw new NotFoundException(ValidateMessage.NOT_FOUND.getDescription());
	}

	@Override
	public List<HistoryTask> findAll() {
		return historyTaskRepository.findAllHistoryTasks();
	}

	@Override
	public void update(HistoryTask historyTask) {
		historyTaskValidator.validateData(historyTask, false);
		historyTaskRepository.save(historyTask);
	}

	@Override
	public void deleteAllByUser(Integer userId) {
		List<HistoryTask> historyTaskList = historyTaskRepository.findAllHistoryTasksByUser(userId);
		if (null != historyTaskList && !historyTaskList.isEmpty()) {
			historyTaskRepository.deleteAll(historyTaskList);
		}
	}

}
