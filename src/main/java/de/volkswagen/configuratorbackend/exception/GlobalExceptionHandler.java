package de.volkswagen.configuratorbackend.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CarConfigNotFoundException.class)
    public final ResponseEntity<?> handleCarConfigNotFoundException(Exception exception, WebRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(DataAccessException.class)
    public final ResponseEntity<?> handleDataAccessException(Exception exception, WebRequest request){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(CarConfigAttributeNotFoundException.class)
    public final ResponseEntity<?> handleCarConfigAttributeNotFoundException(Exception exception, WebRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}