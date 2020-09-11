package com.thoughtworks.capacity.gtb.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handle(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        ErrorResult errorResult = new ErrorResult(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<ErrorResult> handle(UserExistException ex) {
        ErrorResult errorResult = new ErrorResult(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(UserLoginException.class)
    public ResponseEntity<ErrorResult> handle(UserLoginException ex) {
        ErrorResult errorResult = new ErrorResult(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }
}
