package com.eduhub.eduhub_backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
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
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIndexError(IllegalArgumentException ex, HttpServletRequest request){
        ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                "IllegalArgument", ex.getMessage(),request.getRequestURI());
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request){
        ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(),HttpStatus.METHOD_NOT_ALLOWED.value(),"Exception",ex.getMessage(),request.getRequestURI());
        return new ResponseEntity<>(errorResponse,HttpStatus.METHOD_NOT_ALLOWED);
    }
}
