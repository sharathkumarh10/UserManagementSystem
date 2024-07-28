package com.jsp.um.utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.um.entity.User;
import com.jsp.um.exception.UserNotFoundByIdException;

@RestControllerAdvice
public class ApplicationHandler {

	private ResponseEntity<ErrorStructure> errorResponse(HttpStatus status, String message, String rootCause) {

		return ResponseEntity.status(status).body(
				new ErrorStructure().setErrorMessage(message).setRootCause(rootCause).setStatusCode(status.value()));

	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure> userNotFoundException(UserNotFoundByIdException userNotFoundException) {

		return errorResponse(HttpStatus.NOT_FOUND, "This user is not present", userNotFoundException.getMessage());

	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<Map<String,String>>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
//	public ResponseEntity<ErrorStructure<List<String>>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
		List<ObjectError> errors=ex.getAllErrors();
		Map<String,String> allErrors=new HashMap<>();
		errors.forEach(error -> {
			FieldError fr=(FieldError) error;
			String field=fr.getField();
			String message=fr.getDefaultMessage();
			allErrors.put(field, message);
		});
		
		
//		List<String> allErrors=errors.stream().map(error->{
//				FieldError fr=(FieldError) error;
//		return fr.getDefaultMessage();
//		}).toList();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				new ErrorStructure<Map<String,String>>().setStatusCode(HttpStatus.BAD_REQUEST.value()).setRootCause(allErrors).setErrorMessage("Invalid Input"));
		
	}
}
