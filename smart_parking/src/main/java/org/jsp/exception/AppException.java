package org.jsp.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppException {
	@ExceptionHandler
	public ResponseEntity<Map<String, Object>> userExists(UserAlreadyExists e){
		HashMap<String, Object> error = new HashMap<String, Object>();
		error.put("timestamp", LocalDateTime.now());
		error.put("status", HttpStatus.BAD_REQUEST);
		error.put("error", "User Exists");
		error.put("message", e.getMessage());
		
		return new ResponseEntity<Map<String,Object>>(error,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	public ResponseEntity<Map<String, Object>> userNotFound(UserNotFoundException e){
		HashMap<String, Object> error = new HashMap<String, Object>();
		error.put("timestamp", LocalDateTime.now());
		error.put("status", HttpStatus.BAD_REQUEST);
		error.put("error", "User Not Found with this email");
		error.put("message", e.getMessage());
		
		return new ResponseEntity<Map<String,Object>>(error,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	public ResponseEntity<Map<String, Object>> passwordIncorrect(PasswordIncorrect e){
		HashMap<String, Object> error = new HashMap<String, Object>();
		error.put("timestamp", LocalDateTime.now());
		error.put("status", HttpStatus.BAD_REQUEST);
		error.put("error", "Wrong Password");
		error.put("message", e.getMessage());
		
		return new ResponseEntity<Map<String,Object>>(error,HttpStatus.BAD_REQUEST);
	}
	
}
