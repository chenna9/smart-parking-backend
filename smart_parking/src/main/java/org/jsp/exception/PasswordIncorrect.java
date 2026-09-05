package org.jsp.exception;

public class PasswordIncorrect extends RuntimeException {
	public PasswordIncorrect(String msg){
		super(msg);
	}
}
