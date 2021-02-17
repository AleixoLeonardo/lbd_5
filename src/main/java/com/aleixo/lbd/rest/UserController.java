package com.aleixo.lbd.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.aleixo.lbd.exception.ValidateException;
import com.aleixo.lbd.model.User;
import com.aleixo.lbd.service.UserService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ResponseEntity<String> create(@RequestBody User user) {
		try {
			userService.save(user);
			return new ResponseEntity<String>("", HttpStatus.OK);
		} catch (ValidateException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(path = "/", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@RequestBody User user) {
		try {
			userService.update(user);
			return new ResponseEntity<String>("", HttpStatus.OK);
		} catch (ValidateException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> findById(@PathVariable Integer id) {
		try {
			return new ResponseEntity<User>(userService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		try {
			userService.delete(id);
			return new ResponseEntity<String>("", HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
