package com.aleixo.lbd.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.aleixo.lbd.constants.ValidateMessage;
import com.aleixo.lbd.exception.NotFoundException;
import com.aleixo.lbd.exception.ValidateException;
import com.aleixo.lbd.model.HistoryTask;
import com.aleixo.lbd.repository.HistoryTaskRepository;
import com.aleixo.lbd.service.HistoryTaskService;
import com.aleixo.lbd.service.validator.HistoryTaskValidator;

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
	@PreAuthorize("isAuthenticated()")
	public void delete(Integer id) throws NotFoundException {
		Optional<HistoryTask> historyTask = historyTaskRepository.findById(id);
		if (historyTask.isPresent()) {
			historyTaskRepository.delete(historyTask.get());
			return;
		}
		throw new NotFoundException(ValidateMessage.NOT_FOUND.getDescription());
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<HistoryTask> findAll() {
		return historyTaskRepository.findAllHistoryTasks();
	}

	@Override
	public void update(HistoryTask historyTask) {
		historyTaskValidator.validateData(historyTask, false);
		historyTaskRepository.save(historyTask);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public void deleteAllByUser(Integer userId) {
		List<HistoryTask> historyTaskList = historyTaskRepository.findAllHistoryTasksByUser(userId);
		if (null != historyTaskList && !historyTaskList.isEmpty()) {
			historyTaskRepository.deleteAll(historyTaskList);
		}
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public HistoryTask findById(Integer id) throws NotFoundException {
		Optional<HistoryTask> historyTask = historyTaskRepository.findById(id);
		if (historyTask.isPresent()) {
			return historyTask.get();
		}
		throw new NotFoundException(ValidateMessage.NOT_FOUND.getDescription());
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<HistoryTask> findByIdTaskPeriod(Integer taskId, Long start, Long end) throws NotFoundException {
		try {
			return historyTaskRepository.findAllByTaskIdPeriod(taskId, new Date(start), new Date(end));
		} catch (Exception e) {
			throw new ValidateException(ValidateMessage.INVALID_DATA_ERROR.getDescription());
		}
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public void delete(List<HistoryTask> historyTaskList) throws NotFoundException {
		historyTaskRepository.deleteAll(historyTaskList);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<HistoryTask> findByUser(Integer id) throws NotFoundException {
		return historyTaskRepository.findAllHistoryTasksByUser(id);
	}

}
