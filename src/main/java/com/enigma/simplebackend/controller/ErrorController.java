package com.enigma.simplebackend.controller;

import com.enigma.simplebackend.exception.DuplicateException;
import com.enigma.simplebackend.exception.NotFoundException;
import com.enigma.simplebackend.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DuplicateException.class})
    public ResponseEntity<Object> handleDuplicateException(DuplicateException duplicateException){
        ErrorResponse errorResponse  = new ErrorResponse(
                HttpStatus.CONFLICT.value(),HttpStatus.CONFLICT.getReasonPhrase(), duplicateException.getMessage()
        );

        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }

}