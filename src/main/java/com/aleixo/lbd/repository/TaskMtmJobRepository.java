package com.aleixo.lbd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aleixo.lbd.model.TaskMtmJob;

@Repository
public interface TaskMtmJobRepository extends CrudRepository<TaskMtmJob, Integer>{

}
