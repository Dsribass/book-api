package com.example.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class JacksonConfiguration {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getTimeZone("UTC")); // force UTC globally
        mapper.registerModule(new JavaTimeModule()); // support for java.time.*
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // output ISO 8601
        return mapper;
    }
}
