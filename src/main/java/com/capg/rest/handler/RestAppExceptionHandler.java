package com.capg.rest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.capg.rest.exception.ProductNotFoundException;
import com.capg.rest.exception.ReviewNotFoundException;

@ControllerAdvice
public class RestAppExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<HttpStatus> productNotFoundExceptionHandler(ProductNotFoundException ex)
	{
		System.out.println(ex);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler(ReviewNotFoundException.class)
	public ResponseEntity<String> reviewNotFoundExceptionHandler(ReviewNotFoundException ex)
	{
		System.out.println(ex);
		return new ResponseEntity<String>("Reviews Not found", HttpStatus.NO_CONTENT);
	}
	
}
