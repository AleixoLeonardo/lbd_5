package com.aleixo.lbd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aleixo.lbd.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
	@Query(value = "SELECT t FROM Task t")
	List<Task> findAllTasks();
	
	@Query(value = "SELECT t FROM Task t JOIN t.taskMtmJobList tmj WHERE tmj.taskId.id = :jobId")
	List<Task> findAllByJob(@Param("jobId") Integer id);
}