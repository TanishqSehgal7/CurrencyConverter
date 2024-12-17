package com.example.currencyconverter.dto;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConverterDto {

    private String fromCurrency;

    private String toCurrency;

    private int units;

    private Double totalCost;

}