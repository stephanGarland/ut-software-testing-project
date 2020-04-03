package edu.utexas.ee360t.book.exception;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.core.annotation.Order;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.utexas.ee360t.book.dto.ErrorDto;

/**
 * This class handles exceptions and transforms them into meaningful
 * responses to return to the user.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
   @ExceptionHandler(EntityNotFoundException.class)
   protected ResponseEntity<ErrorDto> handleEntityNotFound(EntityNotFoundException ex) {
	   ErrorDto e = new ErrorDto(HttpStatus.NO_CONTENT, ex.getMessage());
	   return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e);
   }
   
   @ExceptionHandler(ConstraintViolationException.class)
   protected ResponseEntity<ErrorDto> handleEntityNotFound(ConstraintViolationException ex) {
	   StringBuilder sb = new StringBuilder();
	   ex.getConstraintViolations().forEach(violation ->{
		   sb.append(violation.getMessage()).append("\n");
	   });
	   
	   ErrorDto e = new ErrorDto(HttpStatus.BAD_REQUEST, sb.toString());
	   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
   }
}
