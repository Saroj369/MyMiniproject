package com.google.phonebook.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ContactNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleContactNotFoundException(ContactNotFoundException ex, WebRequest request){
		
		List<String> details = new ArrayList<>();
		
		details.add(ex.getMessage());
		
		ErrorResponse err = new ErrorResponse("INCORRECT_REQUEST", details);
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
}
