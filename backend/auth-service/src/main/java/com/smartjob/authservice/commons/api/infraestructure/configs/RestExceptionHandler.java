package com.smartjob.authservice.commons.api.infraestructure.configs;
import com.smartjob.authservice.commons.api.domains.data.UtilApi;
import com.smartjob.authservice.commons.api.domains.exception.BaseException;
import com.smartjob.authservice.commons.api.domains.exception.ErrorResponse;
import com.smartjob.authservice.commons.api.domains.exception.ResponseException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;

import static com.smartjob.authservice.commons.api.domains.exception.ResponseException.responseExceptionCreate;


@RestControllerAdvice
public class RestExceptionHandler  {
    @Value("${smartjob.api.auth.title.message.error}")
    private String titleMessageError;
    @Autowired
    UtilApi utilApil;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (e instanceof BaseException) {
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status)
                .body( responseExceptionCreate( e));
    }
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity<?> onConstraintValidationException(
            ConstraintViolationException e) {
        List<ResponseException> errorsList = new ArrayList();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            errorsList.add( responseExceptionCreate(utilApil.getMessage(String.format(titleMessageError,violation.getPropertyPath().toString()))));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                                    .errors(errorsList)
                                    .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity<?> onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        List<ResponseException> errorsList = new ArrayList();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorsList.add( responseExceptionCreate(utilApil.getMessage(String.format(titleMessageError,fieldError.getField()))));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .errors(errorsList)
                        .build());
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