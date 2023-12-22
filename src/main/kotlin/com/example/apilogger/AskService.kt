package com.example.apilogger

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AskService(
    private val askFeignClient: AskFeignClient
) {

    fun askSuccess(): ResponseEntity<Any> {
        return askFeignClient.success()
    }

    fun askBadRequest(): ResponseEntity<Any> {
        return askFeignClient.badRequest()
    }

    fun askInternalServerError(): ResponseEntity<Any> {
        return askFeignClient.internalServerError()
    }
}