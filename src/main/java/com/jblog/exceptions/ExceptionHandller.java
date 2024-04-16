package com.jblog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jblog.utils.ErrorMessage;

@RestControllerAdvice
public class ExceptionHandller {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> error = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach(err->{
			String fieldName = ((FieldError) err).getField();
			String msg = err.getDefaultMessage();
			error.put(fieldName, msg);
		});
		return new ResponseEntity<Map<String,String>>(error, HttpStatus.BAD_REQUEST);
	}
}
