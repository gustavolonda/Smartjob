package com.smartjob.authservice.commons.api.infraestructure.configs;
import com.smartjob.authservice.commons.api.domains.exception.BaseException;
import com.smartjob.authservice.commons.api.domains.exception.ErrorResponse;
import com.smartjob.authservice.commons.api.domains.exception.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import static com.smartjob.authservice.commons.api.domains.exception.ResponseException.responseExceptionCreate;


@RestControllerAdvice
public class RestExceptionHandler  {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (e instanceof BaseException) {
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status)
                .body( responseExceptionCreate( e));
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleException(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(responseExceptionCreate( e));
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleException2(NoHandlerFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseExceptionCreate( e));
    }
}