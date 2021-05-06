package com.aleixo.lbd.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.aleixo.lbd.exception.NotFoundException;
import com.aleixo.lbd.model.TaskMtmJob;
import com.aleixo.lbd.rest.view.TaskMtmJobView;
import com.aleixo.lbd.service.TaskMtmJobService;
import com.fasterxml.jackson.annotation.JsonView;


@RestController
@RequestMapping("/task_mtm_job")
@CrossOrigin()
public class TaskMtmJobController {

	@Autowired
	private TaskMtmJobService taskMtmJobService;
	
	
	@RequestMapping(path = "/{idTask}", method = RequestMethod.GET)
	@JsonView(TaskMtmJobView.TaskMtmJobFull.class)
	public ResponseEntity<List<TaskMtmJob>> findById(@PathVariable Integer idTask) {
		try {
			return new ResponseEntity<List<TaskMtmJob>>(taskMtmJobService.findByTask(idTask), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		try {
			taskMtmJobService.delete(id);
			return new ResponseEntity<String>("", HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
