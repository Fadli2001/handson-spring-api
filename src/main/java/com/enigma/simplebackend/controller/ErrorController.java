package com.enigma.simplebackend.controller;

import com.enigma.simplebackend.exception.DuplicateException;
import com.enigma.simplebackend.exception.NotAcceptableException;
import com.enigma.simplebackend.exception.NotFoundException;
import com.enigma.simplebackend.response.ErrorResponse;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.validation.ConstraintViolationException;

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

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(
                badRequest.value(),badRequest.getReasonPhrase(), e.getMessage()
        );
        return new ResponseEntity<>(errorResponse, badRequest);
    }

    @ExceptionHandler(value = {NotAcceptableException.class})
    public ResponseEntity<Object> handleNotAcceptableException(NotAcceptableException e) {
        HttpStatus notAcceptable = HttpStatus.NOT_ACCEPTABLE;
        ErrorResponse errorResponse = new ErrorResponse(

                notAcceptable.value(),notAcceptable.getReasonPhrase(),e.getMessage()
        );
        return new ResponseEntity<>(errorResponse, notAcceptable);
    }


    @ExceptionHandler(value = {SizeLimitExceededException.class})
    public ResponseEntity<Object> handleLimitSize(SizeLimitExceededException exception){
        HttpStatus limitSize = HttpStatus.PAYLOAD_TOO_LARGE;
        ErrorResponse errorResponse = new ErrorResponse(
                limitSize.value(),limitSize.getReasonPhrase(),exception.getMessage()
        );

        return new ResponseEntity<>(errorResponse,limitSize);
    }

    @ExceptionHandler(value = {MissingServletRequestPartException.class})
    public ResponseEntity<Object> missingRequestPart(MissingServletRequestPartException exception){
        HttpStatus err = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(
                err.value(),err.getReasonPhrase(),exception.getMessage()
        );

        return new ResponseEntity<>(errorResponse,err);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<Object> notReadable(HttpMessageNotReadableException exception){
        HttpStatus readable = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(
                readable.value(),readable.getReasonPhrase(),exception.getLocalizedMessage()
        );

        return new ResponseEntity<>(errorResponse,readable);
    }


}