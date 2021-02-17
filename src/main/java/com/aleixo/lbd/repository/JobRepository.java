package com.aleixo.lbd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aleixo.lbd.model.Job;

@Repository
public interface JobRepository extends CrudRepository<Job, Integer>{

}
