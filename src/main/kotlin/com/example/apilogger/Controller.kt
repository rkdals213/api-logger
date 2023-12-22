package com.example.apilogger

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
    private val askService: AskService
) {

    @PostMapping("/success")
    fun success(): ResponseEntity<Any> {
        Thread.sleep(3000)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/badRequest")
    fun badRequest(): ResponseEntity<Any> {
        Thread.sleep(3000)
        return ResponseEntity.badRequest().build()
    }

    @PostMapping("/internalServerError")
    fun internalServerError(): ResponseEntity<Any> {
        Thread.sleep(3000)
        return ResponseEntity.internalServerError().build()
    }

    @PostMapping("/ask/success")
    fun askSuccess(): ResponseEntity<Any> {
        return askService.askSuccess()
    }

    @PostMapping("/ask/badRequest")
    fun askBadRequest(): ResponseEntity<Any> {
        return askService.askBadRequest()
    }

    @PostMapping("/ask/internalServerError")
    fun askInternalServerError(): ResponseEntity<Any> {
        return askService.askInternalServerError()
    }
}