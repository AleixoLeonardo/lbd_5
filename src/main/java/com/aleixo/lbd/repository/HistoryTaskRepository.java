package com.aleixo.lbd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aleixo.lbd.model.HistoryTask;

@Repository
public interface HistoryTaskRepository extends CrudRepository<HistoryTask, Integer>{

}
