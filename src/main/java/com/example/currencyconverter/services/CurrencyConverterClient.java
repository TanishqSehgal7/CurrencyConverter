package com.example.currencyconverter.services;

import com.example.currencyconverter.advices.CurrencyConverterResponse;

public interface CurrencyConverterClient {

    CurrencyConverterResponse getTotalCostFromOneUnitToAnother(
            String fromCurrency, String toCurrency, int totalCost);
}
