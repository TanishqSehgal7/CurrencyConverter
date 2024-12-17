package com.example.currencyconverter.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Map;

@Data
@Getter
@Setter
@NoArgsConstructor
public class CurrencyRateEntity {

    @JsonProperty("data")
    private Map<String, Double> exchangeRates;
}
