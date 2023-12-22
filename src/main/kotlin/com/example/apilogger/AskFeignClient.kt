package com.example.apilogger

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(value = "ask", url = "http://localhost:8080")
interface AskFeignClient {

    @PostMapping("/success")
    fun success(): ResponseEntity<Any>

    @PostMapping("/badRequest")
    fun badRequest(): ResponseEntity<Any>

    @PostMapping("/internalServerError")
    fun internalServerError(): ResponseEntity<Any>
}