package com.lianji.timeendlessapp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
class TimeEndlessAppApplication {

    static void main(String[] args) {
        SpringApplication.run TimeEndlessAppApplication, args
    }
}
