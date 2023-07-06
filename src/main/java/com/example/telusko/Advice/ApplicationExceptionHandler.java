package com.example.telusko.Advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.telusko.Exceptions.UserNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserNotFoundException.class)
	public Map<String, String> handleBuisnessException(UserNotFoundException ex){
		Map<String, String> errorMap= new HashMap<>();
		errorMap.put("error message", ex.getMessage());
		return errorMap;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NullPointerException.class)
	public Map<String,String> handleBuisnessExcepton2(NullPointerException ex2){
		Map<String,String> errorMap2= new HashMap<>();
		errorMap2.put("errorMessage", ex2.getMessage());
		return errorMap2;
	}
	
}
