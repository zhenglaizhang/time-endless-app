package com.lianji.te.model.request

import org.springframework.web.multipart.MultipartFile

class UploadModel {

    String name

    String description

    MultipartFile[] files
}
