package com.lianji.te.rest.model.request

import groovy.transform.Canonical
import org.springframework.web.multipart.MultipartFile

@Canonical
class PhotoRequest {
    String name, description
    MultipartFile file

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    LocalDate taken_time
}
