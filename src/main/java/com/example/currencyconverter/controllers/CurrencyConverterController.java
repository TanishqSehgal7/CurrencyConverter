package com.example.currencyconverter.controllers;

import com.example.currencyconverter.advices.CurrencyConverterResponse;
import com.example.currencyconverter.advices.ResourceNotFoundException;
import com.example.currencyconverter.services.CurrencyConverterClientServiceImplementation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/convertCurrency")
public class CurrencyConverterController {


    private final CurrencyConverterClientServiceImplementation currencyConverterClientServiceImplementation;

    public CurrencyConverterController(CurrencyConverterClientServiceImplementation currencyConverterClientServiceImplementation) {
        this.currencyConverterClientServiceImplementation = currencyConverterClientServiceImplementation;
    }


    @GetMapping()
    public ResponseEntity<CurrencyConverterResponse> convertCurrency(
            @RequestParam(required = true, name = "fromCurrency") String fromCurrency,
            @RequestParam(required = true, name = "toCurrency") String toCurrency,
            @RequestParam(required = false, name = "units", defaultValue = "1") int units) {

        if(fromCurrency!=null && toCurrency!=null) {
            return ResponseEntity.ok(currencyConverterClientServiceImplementation
                    .getTotalCostFromOneUnitToAnother(fromCurrency, toCurrency, units));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}