package com.lianji.timeendlessapp.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ApiConig {

    @Bean
    ObjectMapper objectMapper() {
        new ObjectMapper()
    }

    @Bean
    ObjectWriter objectWriter(ObjectMapper objectMapper) {
        objectMapper.writerWithDefaultPrettyPrinter()
    }
}
