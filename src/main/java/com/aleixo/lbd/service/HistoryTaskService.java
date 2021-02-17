package com.aleixo.lbd.service;

import java.util.List;

import com.aleixo.lbd.model.HistoryTask;
import com.aleixo.lbd.exception.NotFoundException;

public interface HistoryTaskService {
	public void save(HistoryTask historyTask);

	public HistoryTask findById(Integer id) throws NotFoundException;

	public void delete(Integer id) throws NotFoundException;

	public List<HistoryTask> findAll();

	public void deleteAllByUser(Integer userId);

	public void update(HistoryTask historyTask);

}
