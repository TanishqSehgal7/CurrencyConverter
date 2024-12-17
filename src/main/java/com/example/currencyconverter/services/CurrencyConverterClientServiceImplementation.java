package com.example.currencyconverter.services;

import com.example.currencyconverter.advices.CurrencyConverterResponse;
import com.example.currencyconverter.advices.ResourceNotFoundException;
import com.example.currencyconverter.dto.CurrencyConverterDto;
import com.example.currencyconverter.entities.CurrencyConverter;
import com.example.currencyconverter.repositories.CurrencyConverterRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CurrencyConverterClientServiceImplementation implements CurrencyConverterClient {


    private final RestClient restClient;
    private final CurrencyConverterRepository currencyConverterRepository;
    private final ModelMapper modelMapper;

    public CurrencyConverterClientServiceImplementation(CurrencyConverterRepository currencyConverterRepository
    , ModelMapper modelMapper, RestClient restClient) {
        this.currencyConverterRepository = currencyConverterRepository;
        this.modelMapper = modelMapper;
        this.restClient = restClient;
    }


    public Map<String,Double> getRestClientResponse() {

        try {
            Map<String,Double> exchangeRate = restClient.get()
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        throw new ResourceNotFoundException("Could not fetch exchange rates!");
                    })
                    .body(new ParameterizedTypeReference<Map<String, Double>>() {
                    });
            return exchangeRate;
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

            Double totalCost = (units/fromCurrencyRate) * toCurrencyRate;

            CurrencyConverterResponse response = new CurrencyConverterResponse();
            CurrencyConverterDto currencyConverterDto = new CurrencyConverterDto();
            currencyConverterDto.setFromCurrency(fromCurrency);
            currencyConverterDto.setToCurrency(toCurrency);
            currencyConverterDto.setUnits(units);
            currencyConverterDto.setTotalCost(totalCost);
            response.setCurrencyConverterDto(currencyConverterDto);

            currencyConverterRepository.save(modelMapper.map(currencyConverterDto, CurrencyConverter.class));

            return response;
        } else {
            return null;
        }
    }
}
