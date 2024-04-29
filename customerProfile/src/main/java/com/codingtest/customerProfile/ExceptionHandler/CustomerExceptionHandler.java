package com.codingtest.customerProfile.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class CustomerExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleInternalServerError(Exception ex) {
      
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred. Please try again later.");
    }
	
	 @ExceptionHandler(NoHandlerFoundException.class)
	    public ResponseEntity<Object> handleNotFound(NoHandlerFoundException ex) {
	       
	        ex.printStackTrace();
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("The requested resource is not found.");
	    }
	 
	 @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	    public ResponseEntity<Object> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
	       
	        ex.printStackTrace();
	        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
	                .body("The request method is not supported for this endpoint.");
	    }
	 
	 @ExceptionHandler(MethodNotAllowedException.class)
	    public ResponseEntity<Object> handleMethodNotAllowed(MethodNotAllowedException ex) {
	      
	        ex.printStackTrace();
	        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
	                .body("The request method is not allowed for this endpoint.");
	    }
}
