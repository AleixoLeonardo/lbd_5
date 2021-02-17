package com.aleixo.lbd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aleixo.lbd.model.Job;

@Repository
public interface JobRepository extends CrudRepository<Job, Integer> {
	@Query(value = "SELECT j FROM Job j")
	List<Job> findAllJobs();
}
