package com.aleixo.lbd.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aleixo.lbd.model.HistoryTask;

@Repository
public interface HistoryTaskRepository extends CrudRepository<HistoryTask, Integer> {
	@Query(value = "SELECT h FROM HistoryTask h")
	List<HistoryTask> findAllHistoryTasks();

	@Query(value = "SELECT h FROM HistoryTask h WHERE h.userId.id = :userId")
	List<HistoryTask> findAllHistoryTasksByUser(@Param("userId") Integer userId);

	@Query(value = "SELECT h FROM HistoryTask h WHERE h.taskId.id = :taskId AND h.historyDate >= :start AND h.historyDate <= :end")
	List<HistoryTask> findAllByTaskIdPeriod(@Param("taskId") Integer taskId, Date start, Date end);
	
	@Query(value = "SELECT h FROM HistoryTask h WHERE h.userId.role = :role")
	List<HistoryTask> findAllByRole(@Param("role") String role);
	
}
