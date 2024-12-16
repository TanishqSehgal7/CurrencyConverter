package com.example.currencyconverter.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConverterDto {


    private Long id;

    private String fromCurrency;

    private String toCurrency;

    private int units;

    private int totalCost;

    private LocalDateTime timeStamp;
}
