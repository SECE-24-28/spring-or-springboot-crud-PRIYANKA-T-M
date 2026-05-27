package com.eduhub.eduhub_backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request){
        ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(), ex.getMessage(),request.getRequestURI());
        return new ResponseEntity<ErrorResponse>(
                errorResponse,
                HttpStatus.NOT_FOUND);
    }
}
