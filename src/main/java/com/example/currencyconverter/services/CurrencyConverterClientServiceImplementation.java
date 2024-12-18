package com.example.currencyconverter.services;

import com.example.currencyconverter.advices.CurrencyConverterResponse;
import com.example.currencyconverter.advices.InvalidParameterException;
import com.example.currencyconverter.advices.ResourceNotFoundException;
import com.example.currencyconverter.dto.CurrencyConverterDto;
import com.example.currencyconverter.entities.CurrencyConverter;
import com.example.currencyconverter.entities.CurrencyRateEntity;
import com.example.currencyconverter.repositories.CurrencyConverterRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class CurrencyConverterClientServiceImplementation implements CurrencyConverterClient {


    private final RestClient restClient;
    private final CurrencyConverterRepository currencyConverterRepository;
    private final ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(CurrencyConverterClientServiceImplementation.class);

    public CurrencyConverterClientServiceImplementation(CurrencyConverterRepository currencyConverterRepository
    , ModelMapper modelMapper, @Qualifier("currencyConverterClient") RestClient restClient) {
        this.currencyConverterRepository = currencyConverterRepository;
        this.modelMapper = modelMapper;
        this.restClient = restClient;
    }


    public Map<String,Double> getRestClientResponse() {

        try {
            System.out.println("Inside Try Block");
            CurrencyRateEntity exchangeRate = restClient.get()
                    .uri("")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        throw new ResourceNotFoundException("Could not fetch exchange rates!");
                    })
                    .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                        throw new RuntimeException("Server Error while fetching exchange rates!");
                    })
                    .body(CurrencyRateEntity.class);
            System.out.println("Response received from API: " + exchangeRate);
            System.out.println("Exchange Rates: " + exchangeRate.getExchangeRates());
            return exchangeRate.getExchangeRates();
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    @Override
    public CurrencyConverterResponse getTotalCostFromOneUnitToAnother(String fromCurrency, String toCurrency, int units) {

        Map<String, Double> exchangeRates = getRestClientResponse();

        if(exchangeRates!=null) {
            Double fromCurrencyRate = exchangeRates.getOrDefault(fromCurrency,1.0);
            Double toCurrencyRate = exchangeRates.getOrDefault(toCurrency, 1.0);

            System.out.println("From Currency: " + fromCurrency);
            System.out.println("From Currency Rate: " + fromCurrencyRate);
            System.out.println("To Currency: " + toCurrency);
            System.out.println("To Currency Rate: " + toCurrencyRate);

            Double totalCost = (units/fromCurrencyRate) * toCurrencyRate;
            totalCost = Math.round(totalCost*100.0)/100.0;

            System.out.println("Total Cost: " + totalCost);

            CurrencyConverterResponse response = new CurrencyConverterResponse();
            CurrencyConverterDto currencyConverterDto = new CurrencyConverterDto();
            currencyConverterDto.setFromCurrency(fromCurrency);
            currencyConverterDto.setToCurrency(toCurrency);
            currencyConverterDto.setUnits(units);
            currencyConverterDto.setTotalCost(totalCost);
            response.setCurrencyConverterDto(currencyConverterDto);

            CurrencyConverter currencyConverter = new CurrencyConverter();
            currencyConverter.setFromCurrency(fromCurrency);
            currencyConverter.setToCurrency(toCurrency);
            currencyConverter.setUnits(units);
            currencyConverter.setTotalCost(totalCost);

            currencyConverterRepository.save(currencyConverter);

            return response;
        } else {
            throw new InvalidParameterException("fromCurrency and toCurrency cannot be null");
        }
    }
}
