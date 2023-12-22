package com.example.apilogger

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiLoggerApplication

fun main(args: Array<String>) {
    runApplication<ApiLoggerApplication>(*args)
}
