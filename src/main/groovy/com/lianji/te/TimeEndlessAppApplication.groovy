package com.lianji.te

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication(scanBasePackages = ["com.lianji.te"])
@EnableAutoConfiguration
@ComponentScan("com.lianji.te")
class TimeEndlessAppApplication {

    static void main(String[] args) {
        SpringApplication.run TimeEndlessAppApplication, args
    }
}
