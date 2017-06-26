package com.lianji.te

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

import javax.servlet.http.HttpServletRequest
// todo how to enable this
//http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-error-handling
//@ControllerAdvice
class RestGlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(MultipartException.class)
    @ResponseBody
    ResponseEntity handleControllerException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request)
        new ResponseEntity(ex.getMessage(), status)
    }

    static HttpStatus getStatus(HttpServletRequest httpServletRequest) {
        Integer statusCode = httpServletRequest.getAttribute("javax.servlet.error.status_code") as Integer
        if (statusCode == null) {
            HttpStatus.INTERNAL_SERVER_ERROR
        } else {
            HttpStatus.valueOf(statusCode)
        }
    }
}
