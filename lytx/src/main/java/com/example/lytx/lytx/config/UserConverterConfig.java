package com.example.lytx.lytx.config;

import com.example.lytx.lytx.converter.UserConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConverterConfig {

    @Bean
    public UserConverter getUserConverterService() {
        return new UserConverter();
    }
}
