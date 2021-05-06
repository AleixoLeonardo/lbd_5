package com.aleixo.lbd.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.aleixo.lbd.exception.ValidateException;
import com.aleixo.lbd.model.Task;
import com.aleixo.lbd.rest.view.TaskView;
import com.aleixo.lbd.service.TaskService;
import com.fasterxml.jackson.annotation.JsonView;

import javassist.NotFoundException;

@RestController
@RequestMapping("/task")
@CrossOrigin()
public class TaskController {
	@Autowired
	private TaskService taskService;

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ResponseEntity<String> create(@RequestBody Task task) {
		try {
			return new ResponseEntity<String>(taskService.save(task).toString(), HttpStatus.OK);
		} catch (ValidateException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(path = "/", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@RequestBody Task task) {
		try {
			taskService.update(task);
			return new ResponseEntity<String>("", HttpStatus.OK);
		} catch (ValidateException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	@JsonView(TaskView.TaskResume.class)
	public ResponseEntity<List<Task>> findAll() {
		return new ResponseEntity<List<Task>>(taskService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	@JsonView(TaskView.TaskFull.class)
	public ResponseEntity<Task> findById(@PathVariable Integer id) {
		try {
			return new ResponseEntity<Task>(taskService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	
	@RequestMapping(path = "job/{id}", method = RequestMethod.GET)
	@JsonView(TaskView.TaskFull.class)
	public ResponseEntity<List<Task>> findByUserJob(@PathVariable Integer id) {
		try {
			return new ResponseEntity<List<Task>>(taskService.findByJob(id), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		try {
			taskService.delete(id);
			return new ResponseEntity<String>("", HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
