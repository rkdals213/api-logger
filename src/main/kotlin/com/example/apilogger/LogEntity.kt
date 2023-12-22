package com.example.apilogger

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
class LogEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @CreatedDate
    var createdDateTime: LocalDateTime? = null,

    @LastModifiedDate
    var updatedDateTime: LocalDateTime? = null,

    val methodName: String,

    val requestData: String,

    val body: String,

    @Enumerated(value = EnumType.STRING)
    var httpStatus: HttpStatus? = null,
) {
    override fun toString(): String {
        return "LogEntity(id=$id, createdDateTime=$createdDateTime, updatedDateTime=$updatedDateTime, methodName='$methodName', requestData='$requestData', body='$body', httpStatus=$httpStatus)"
    }
}