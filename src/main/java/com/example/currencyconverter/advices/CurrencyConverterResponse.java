package com.example.currencyconverter.advices;
import com.example.currencyconverter.dto.CurrencyConverterDto;
import lombok.*;
import java.time.LocalDateTime;

@Data
public class CurrencyConverterResponse {

    private LocalDateTime timeStamp;

    private CurrencyConverterDto currencyConverterDto;

    private ApiError apiError;

    public CurrencyConverterResponse() {
        this.timeStamp = LocalDateTime.now();
    }

    public CurrencyConverterResponse(CurrencyConverterDto currencyConverterDto) {
        this();
        this.currencyConverterDto = currencyConverterDto;
    }

    public CurrencyConverterResponse(ApiError apiError) {
        this();
        this.apiError = apiError;
    }
}
