package com.aleixo.lbd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aleixo.lbd.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer>{

}
