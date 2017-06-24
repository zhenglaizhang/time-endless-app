package com.lianji.te.rest

import com.lianji.te.model.response.Photo
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import java.time.LocalDate

// GET http://localhost:8080/gallery/photo/v1?taken_date=3012-01-12&album=test
@RestController
@RequestMapping(ResourceConstants.GALLERY_PHOTO_V1)
class PhotoResource {

    @RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<Photo> getPhotos(
            @RequestParam(value = "taken_date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate takenDate,
            @RequestParam(value = "album")
                    String album
    ) {
        new ResponseEntity<>(new Photo(), HttpStatus.OK)
    }

}
