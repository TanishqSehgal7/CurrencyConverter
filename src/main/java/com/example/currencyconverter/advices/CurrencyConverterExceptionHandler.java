package com.example.currencyconverter.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CurrencyConverterExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<CurrencyConverterResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ApiError apiError = ApiError.builder()
                .statusCode(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(new CurrencyConverterResponse(apiError), apiError.getStatusCode());
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<CurrencyConverterResponse> handleException(RuntimeException exception) {
        ApiError apiError = ApiError.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(new CurrencyConverterResponse(apiError), apiError.getStatusCode());
    }
}
