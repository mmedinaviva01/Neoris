package com.neoris.app.exception;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private HttpStatus statusCode;
	
	@Builder
	public ResourceNotFoundException(String message, HttpStatus statusCode) {
		super(message);
		this.statusCode = statusCode;
	}
}
