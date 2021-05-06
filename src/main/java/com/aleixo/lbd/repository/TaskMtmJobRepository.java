package com.aleixo.lbd.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aleixo.lbd.model.TaskMtmJob;

@Repository
public interface TaskMtmJobRepository extends CrudRepository<TaskMtmJob, Integer>{
	@Modifying
	@Query("DELETE TaskMtmJob t WHERE t.id = :id")
	int deleteByIdNative(@Param("id") Integer id);
}
