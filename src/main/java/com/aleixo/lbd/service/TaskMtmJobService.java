package com.aleixo.lbd.service;

import java.util.List;

import com.aleixo.lbd.model.TaskMtmJob;

public interface TaskMtmJobService {
	public List<TaskMtmJob> findByTask(Integer idTask);
}
