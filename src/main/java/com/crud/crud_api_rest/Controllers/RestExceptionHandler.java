package com.crud.crud_api_rest.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.crud.crud_api_rest.Models.ErrorObject;
import com.crud.crud_api_rest.Models.ErrorResponse;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,  HttpStatusCode status, WebRequest request) {
        List<ErrorObject> errors = getErrors(ex);
        ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
        return new ResponseEntity<>(errorResponse, status);
    }

    private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatusCode status, List<ErrorObject> errors) {
        return new ErrorResponse("Requisição possui campos inválidos", status.value(),
                status.toString(), ex.getBindingResult().getObjectName(), errors);
    }

    private List<ErrorObject> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
                .collect(Collectors.toList());
    }
}
