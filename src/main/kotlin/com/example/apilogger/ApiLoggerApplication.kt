package com.example.apilogger

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing
class ApiLoggerApplication

fun main(args: Array<String>) {
    runApplication<ApiLoggerApplication>(*args)
}
