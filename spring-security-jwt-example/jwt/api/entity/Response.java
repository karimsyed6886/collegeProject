package com.javatechie.jwt.api.entity;

import java.util.Date;

import com.javatechie.jwt.api.exception.ExceptionResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
	
	private Date date;
	private String  message;
	private String  details;
	private String  response_code;

}
