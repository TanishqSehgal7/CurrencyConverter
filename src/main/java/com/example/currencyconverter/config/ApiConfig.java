package com.example.currencyconverter.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Bean
    ModelMapper getModeMapper() {
        return new ModelMapper();
    }
}
