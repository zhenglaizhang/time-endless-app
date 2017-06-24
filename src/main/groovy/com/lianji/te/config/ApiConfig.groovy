package com.lianji.te.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ApiConfig {

    @Bean
    ObjectMapper objectMapper() {
        new ObjectMapper()
                .registerModule(new JavaTimeModule())
    }

    @Bean
    ObjectWriter objectWriter(ObjectMapper objectMapper) {
        objectMapper.writerWithDefaultPrettyPrinter()
    }
}
