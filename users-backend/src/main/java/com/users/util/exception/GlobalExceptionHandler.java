package com.users.util.exception;


import com.users.util.exception.customExceptions.*;
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

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRequestException(InvalidRequestException exc, WebRequest request){
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleUserAlreadyExists(UserAlreadyExistsException exc, WebRequest request){
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserRoleException.class)
    public ResponseEntity<ExceptionResponse> handleUserRoleException(UserRoleException exc, WebRequest request){
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
