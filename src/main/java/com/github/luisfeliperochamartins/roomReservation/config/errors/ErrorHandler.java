package com.github.luisfeliperochamartins.roomReservation.config.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(BusinessError.class)
	public ResponseEntity<?> business(BusinessError ex) {
		return ResponseEntity.badRequest().body(ex.getCause().getMessage());
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> notFound(EntityNotFoundException ex) {
		return ResponseEntity.badRequest().body(ex.getCause().getMessage());
	}
}
