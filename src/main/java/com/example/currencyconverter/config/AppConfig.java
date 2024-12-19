package com.example.currencyconverter.config;
import com.example.currencyconverter.auth.AuditorAwareImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditorAwareImpl")
public class AppConfig {
    @Bean
    ModelMapper getModeMapper() {
        return new ModelMapper();
    }

    @Bean
    AuditorAware getAuditorAwareImpl() {
        return new AuditorAwareImpl();
    }
}