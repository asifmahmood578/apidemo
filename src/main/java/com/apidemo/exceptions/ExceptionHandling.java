package com.apidemo.exceptions;

import com.apidemo.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFound(ResourceNotFound ex, WebRequest request){
        ErrorDetails error=new ErrorDetails(
                ex.getMessage(),
                new Date(),
                request.getDescription(true)
        );
        return  new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request){
        ErrorDetails error=new ErrorDetails(
                ex.getMessage(),
                new Date(),
                request.getDescription(true)
        );
        return  new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
