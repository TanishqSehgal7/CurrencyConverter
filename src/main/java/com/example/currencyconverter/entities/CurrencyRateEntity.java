package com.example.currencyconverter.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class CurrencyRateEntity {

    @JsonProperty("data")
    private Map<String, Double> exchangeRates;

    public Map<String, Double> getExchangeRates() {
        return exchangeRates;
    }

    public void setExchangeRates(Map<String, Double> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }
}
