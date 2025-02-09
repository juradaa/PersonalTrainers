package edu.jurada.backend.controllers.errorHandling;

import edu.jurada.backend.exceptions.TripsOverlapException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = {TripsOverlapException.class})
	protected ResponseEntity<Object> handleConflict(RuntimeException exception,
													WebRequest request){
		return handleExceptionInternal(exception,exception.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
}
