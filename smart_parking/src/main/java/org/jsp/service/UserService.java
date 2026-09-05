package org.jsp.service;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.jsp.dao.UserDao;
import org.jsp.dto.Login;
import org.jsp.dto.User;
import org.jsp.exception.PasswordIncorrect;
import org.jsp.exception.UserAlreadyExists;
import org.jsp.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UserService {
	@Autowired
	private UserDao dao;
	public ResponseEntity<Map<String, Object>> saveUser(User user){
		Optional<User> db_user = dao.findUserbyEmail(user.getEmail());
		if(!db_user.isPresent()) {
			dao.saveUser(user);
			HashMap<String, Object> hashMap= new HashMap<>();
			hashMap.put("status", HttpStatus.ACCEPTED);
			hashMap.put("User", "User Added");
			
			return new ResponseEntity<Map<String,Object>>(hashMap,HttpStatus.ACCEPTED);
			
		}
		else {
			throw new UserAlreadyExists("Email Already Exists");
		}
	}
	public ResponseEntity<HashMap<String, Object>> login(Login login){
		Optional<User> db_user = dao.findUserbyEmail(login.getEmail());
		
		if(db_user.isEmpty()) {
			throw new UserNotFoundException("User Not Found");
		}
		User user = db_user.get();
        
		if(!user.getPassword().equals(login.getPassword())) {
			throw new PasswordIncorrect("Password Incorrect");
		}
		HashMap<String, Object> response = new HashMap<String, Object>();
		response.put("role", user.getRole());
		response.put("status", HttpStatus.OK);
		response.put("user_id", user.getId());
		response.put("name", user.getName());
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
}
