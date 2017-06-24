package com.lianji.te.rest.request

import org.springframework.format.annotation.DateTimeFormat

import java.time.LocalDate

class PhotoRequest {
    String name

    String description

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate taken_time
}
