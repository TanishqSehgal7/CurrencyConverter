package com.example.currencyconverter.advices;
import com.example.currencyconverter.dto.CurrencyConverterDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDateTime;

@Data
public class CurrencyConverterResponse {

    private LocalDateTime timeStamp;

    @JsonProperty("Currency_Conversion_Data")
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

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public CurrencyConverterDto getCurrencyConverterDto() {
        return currencyConverterDto;
    }

    public void setCurrencyConverterDto(CurrencyConverterDto currencyConverterDto) {
        this.currencyConverterDto = currencyConverterDto;
    }

    public ApiError getApiError() {
        return apiError;
    }

    public void setApiError(ApiError apiError) {
        this.apiError = apiError;
    }
}
