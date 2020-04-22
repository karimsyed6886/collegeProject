package com.javatechie.jwt.api.exception;

import java.util.Date;

import javax.persistence.Entity;

import com.javatechie.jwt.api.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

	private Date date;
	private String  message;
	private String  details;
	private String Status;
	
}
