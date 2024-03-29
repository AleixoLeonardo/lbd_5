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

import com.aleixo.lbd.exception.NotFoundException;
import com.aleixo.lbd.exception.ValidateException;
import com.aleixo.lbd.model.HistoryTask;
import com.aleixo.lbd.rest.view.HistoryTaskView;
import com.aleixo.lbd.service.HistoryTaskService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/history_task")
@CrossOrigin()
public class HistoryTaskController {

	@Autowired
	private HistoryTaskService historyTaskService;

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ResponseEntity<String> create(@RequestBody HistoryTask historyTask) {
		try {
			historyTaskService.save(historyTask);
			return new ResponseEntity<String>("", HttpStatus.OK);
		} catch (ValidateException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(path = "/", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@RequestBody HistoryTask historyTask) {
		try {
			historyTaskService.update(historyTask);
			return new ResponseEntity<String>("", HttpStatus.OK);
		} catch (ValidateException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ResponseEntity<List<HistoryTask>> findAll() {
		return new ResponseEntity<List<HistoryTask>>(historyTaskService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<HistoryTask> findById(@PathVariable Integer id) {
		try {
			return new ResponseEntity<HistoryTask>(historyTaskService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
	
	@RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
	@JsonView(HistoryTaskView.HistoryTaskResume.class)
	public ResponseEntity<List<HistoryTask>> findByUser(@PathVariable Integer id) {
		try {
			return new ResponseEntity<List<HistoryTask>>(historyTaskService.findByUser(id), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

	@RequestMapping(path = "/{idTask}/inicio/{start}/fim/{end}", method = RequestMethod.GET)
	@JsonView(HistoryTaskView.HistoryTaskResume.class)
	public ResponseEntity<List<HistoryTask>> findByIdTaskPeriod(@PathVariable Integer idTask,
			@PathVariable Long start, @PathVariable Long end) {
		try {
			return new ResponseEntity<List<HistoryTask>>(historyTaskService.findByIdTaskPeriod(idTask, start, end),
					HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		try {
			historyTaskService.delete(id);
			return new ResponseEntity<String>("", HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
