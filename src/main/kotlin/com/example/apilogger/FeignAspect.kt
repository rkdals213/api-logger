package com.example.apilogger

import jakarta.persistence.EntityManager
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*

@Aspect
@Component
class FeignAspect(
    private val entityManager: EntityManager
) {

    @Around("execution(* com.example.apilogger.AskFeignClient..*(..)) ")
    @Throws(Throwable::class)
    fun execute(joinPoint: ProceedingJoinPoint): Any? {
        val methodName = joinPoint.signature.toShortString()
        val annotation = (joinPoint.signature as MethodSignature).method.annotations.firstOrNull {
            listOf(
                PostMapping::class,
                GetMapping::class,
                PutMapping::class,
                DeleteMapping::class,
                PatchMapping::class
            ).contains(it.annotationClass)
        } ?: throw RuntimeException("존재하지 않는 애노테이션")
        val arguments = joinPoint.args

        val logEntity = LogEntity(
            methodName = methodName,
            requestData = annotation.annotationClass.toString(),
            body = arguments.joinToString(",") { it.toString() }
        )

        entityManager.persist(logEntity)
        entityManager.flush()
        entityManager.clear()

        var result: ResponseEntity<*>? = null

        try {
            result = joinPoint.proceed() as ResponseEntity<*>

            println(logEntity)
        } catch (e: Exception) {
            println(e.message)
        } finally {
            val logEntity2 = entityManager.find(LogEntity::class.java, logEntity.id)
            println(logEntity2)

            logEntity2.httpStatus = result!!.statusCode as HttpStatus
            entityManager.persist(logEntity2)
            entityManager.flush()
            entityManager.clear()
        }

        val find = entityManager.find(LogEntity::class.java, logEntity.id)
        println(find)

        return result
    }
}