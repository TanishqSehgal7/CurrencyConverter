package com.example.currencyconverter.advices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CurrencyConverterExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<CurrencyConverterResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new CurrencyConverterResponse(apiError), apiError.getStatusCode());
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<CurrencyConverterResponse> handleException(RuntimeException exception) {
        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(new CurrencyConverterResponse(apiError), apiError.getStatusCode());
    }

    @ExceptionHandler(InvalidParameterException.class)
    ResponseEntity<CurrencyConverterResponse> handleInvalidParameterException(RuntimeException exception) {
        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new CurrencyConverterResponse(apiError), apiError.getStatusCode());
    }

}
