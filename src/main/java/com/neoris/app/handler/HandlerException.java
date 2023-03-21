package com.neoris.app.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.neoris.app.exception.MovementReportException;
import com.neoris.app.exception.RequestException;
import com.neoris.app.exception.ResourceNotFoundException;
import com.neoris.app.model.ResponseModel;

@RestControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler()
	public ResponseEntity<ResponseModel> requestExceptionHandler(RequestException e){
		ResponseModel response = ResponseModel.builder().message(e.getMessage()).build();
		return new ResponseEntity<>(response, e.getStatusCode());
	}
	
	@ExceptionHandler()
	public ResponseEntity<ResponseModel> resourceNotFoundExceptionHandler(ResourceNotFoundException e){
		ResponseModel response = ResponseModel.builder().message(e.getMessage()).build();
		return new ResponseEntity<>(response, e.getStatusCode());
	}
	
	@ExceptionHandler()
	public ResponseEntity<ResponseModel> movementReportExceptionHandler(MovementReportException e){
		ResponseModel response = ResponseModel.builder().message(e.getMessage()).build();
		return new ResponseEntity<>(response, e.getStatusCode());
	}
}
