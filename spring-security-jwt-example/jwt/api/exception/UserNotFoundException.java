package com.javatechie.jwt.api.exception;


@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {


	public UserNotFoundException(String message) {
		super(message);
		//this.message = message;
	}
	
	
	
}
