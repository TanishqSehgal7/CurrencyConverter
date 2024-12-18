package com.example.currencyconverter.advices;

public class InvalidParameterException extends RuntimeException{

    public InvalidParameterException(String message) {
        super(message);
    }
}
