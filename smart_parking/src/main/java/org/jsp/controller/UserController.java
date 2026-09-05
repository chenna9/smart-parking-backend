package org.jsp.controller;

import java.util.HashMap;
import java.util.Map;

import org.jsp.dto.Login;
import org.jsp.dto.User;
import org.jsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/users")
	public ResponseEntity<Map<String, Object>> saveUser(@RequestBody User user) {
		return service.saveUser(user);
	}
	@PostMapping("/login")
	public ResponseEntity<HashMap<String, Object>> login(@RequestBody Login login) {
		return service.login(login);
	}
}
