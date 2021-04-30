package com.aleixo.lbd.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aleixo.lbd.exception.ValidateException;
import com.aleixo.lbd.security.JWTUtils;
import com.aleixo.lbd.security.Login;
import com.aleixo.lbd.security.SecurityServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;

import javassist.NotFoundException;

@RestController
@RequestMapping("/login")
@CrossOrigin()
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private SecurityServiceImpl securityServiceImpl;
	
	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<Login> login(@RequestBody Login login) throws JsonProcessingException, NotFoundException {
		try {
			Authentication auth = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
			authenticationManager.authenticate(auth);
			login.setPassword(null);
			login.setToken(JWTUtils.generateToken(auth, securityServiceImpl.loadUserByUsername(login.getUsername())));
			return new ResponseEntity<Login>(login, HttpStatus.OK);
		} catch (ValidateException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
