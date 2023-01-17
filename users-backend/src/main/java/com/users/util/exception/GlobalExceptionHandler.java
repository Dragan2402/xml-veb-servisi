package com.users.util.exception;


import com.users.util.exception.customExceptions.ObjectNotFoundException;
import com.users.util.exception.customExceptions.UnsupportedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleObjectNotFoundException(ObjectNotFoundException exc, WebRequest request){
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnsupportedTypeException.class)
    public ResponseEntity<ExceptionResponse> handleUnsupportedTypeException(UnsupportedTypeException exc, WebRequest request){
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
