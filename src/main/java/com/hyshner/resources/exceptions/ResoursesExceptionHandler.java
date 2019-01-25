package com.hyshner.resources.exceptions;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hyshner.services.exeptions.DataIntegrityViolationException;
import com.hyshner.services.exeptions.ObjectNotFoudException;

@ControllerAdvice
public class ResoursesExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoudException.class)
	public ResponseEntity<StandardError>objectNotFound(ObjectNotFoudException e, HttpServletRequest request){
		 StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError>dataIntegrity(DataIntegrityViolationException e, HttpServletRequest request){
		 StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

}
