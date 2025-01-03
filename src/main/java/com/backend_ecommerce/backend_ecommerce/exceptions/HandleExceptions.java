package com.backend_ecommerce.backend_ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@ControllerAdvice()
public class HandleExceptions {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundException(ResourceNotFoundException ex, 
    WebRequest request) {
        
        ErrorDetails errorDetails = new ErrorDetails();

        var timestamp = java.time.LocalDateTime.now();

        errorDetails.setTimestamp(timestamp);
        errorDetails.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setDetails(request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        
        var timestamp = java.time.LocalDateTime.now();
        
        errorDetails.setTimestamp(timestamp);
        errorDetails.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setDetails(request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
