package com.lianji.te.model.request

import groovy.transform.Canonical
import org.springframework.web.multipart.MultipartFile

@Canonical
class UploadModel {
    String name, description
    MultipartFile[] files
}
