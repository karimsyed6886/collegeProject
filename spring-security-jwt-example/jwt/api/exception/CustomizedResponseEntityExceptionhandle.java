package com.javatechie.jwt.api.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionhandle extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllTypesofExceptions(Exception ex ,WebRequest request) throws Exception{
		ExceptionResponse obj = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false),HttpStatus.INTERNAL_SERVER_ERROR+"");
		//return new ResponseEntity<Object>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		//return ResponseEntity.ok(obj);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				  .body(obj);
	}
	
	//@SuppressWarnings("static-access")
	@ExceptionHandler(UserNotFoundException.class )
	public ResponseEntity<Object> handleUsernotfoundException(Exception ex ,WebRequest request) throws Exception{
		ExceptionResponse obj = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false),HttpStatus.NO_CONTENT+"");
		return ResponseEntity.ok(obj);
		
	}
	

	  @SuppressWarnings("rawtypes")
	@Override
	  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	      HttpHeaders headers, HttpStatus status, WebRequest request) {
		  ExceptionResponse errorDetails = new ExceptionResponse(new Date(), 
	        "Validation failed",ex.getBindingResult().toString(),400+"");
	    //return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				  .body(errorDetails);
	  }
    
}
