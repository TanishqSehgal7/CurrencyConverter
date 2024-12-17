package com.example.currencyconverter.repositories;

import com.example.currencyconverter.entities.CurrencyConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CurrencyConverterRepository extends JpaRepository<CurrencyConverter, Long> {


}
