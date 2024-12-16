package com.example.currencyconverter.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "CurrencyConverter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrenyConverter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "from_currency", nullable=false)
    private String fromCurrency;

    @Column(name = "to_currency", nullable = false)
    private String toCurrency;

    @Column(name = "units", nullable = false)
    private int units;

    @Column(name = "total_cost", nullable = false)
    private double totalCost;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

}
