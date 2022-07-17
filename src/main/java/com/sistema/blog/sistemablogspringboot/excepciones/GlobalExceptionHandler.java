package com.sistema.blog.sistemablogspringboot.excepciones;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.sistema.blog.sistemablogspringboot.dto.ErrorDetalles;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetalles> manejarResourceNotFoundException(ResourceNotFoundException e,
			WebRequest webRequest) {
		ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), e.getMessage(), webRequest.getDescription(false));

		return new ResponseEntity<>(errorDetalles, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BlogAppException.class)
	public ResponseEntity<ErrorDetalles> manejarBlogAppException(BlogAppException e,
			WebRequest webRequest) {
		ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), e.getMessage(), webRequest.getDescription(false));

		return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetalles> manejarException(Exception e,
			WebRequest webRequest) {
		ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), e.getMessage(), webRequest.getDescription(false));

		return new ResponseEntity<>(errorDetalles, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
