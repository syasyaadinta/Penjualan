package com.tes.Penjualan.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    // Exception For Id Not Found Exception
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(IdNotFoundException exc) {
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Exception For Username Not Found Exception
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(UsernameNotFoundException exc) {
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Exception For Unique Field Exception
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException (UniqueFieldException exc){
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    // Exception For Dependency Exception
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(DependencyException exc) {
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // Exception For Not Allowed Exception
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException (NotAllowedException exc){
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }

    // Exception For Forbidden Exception
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException (ForbiddenException exc){
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.FORBIDDEN.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    // Exception For Method Argument Not Valid Exception
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    String fieldName = "";
                    if (error.getCode().equals("Compare")){
                        fieldName =error.getCode();
                    }
                    else{
                        fieldName = ((FieldError) error).getField();
                    }

                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        errorResponse.setMessage(errors);
        errorResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

}
