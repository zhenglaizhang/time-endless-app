package com.lianji.te.rest

import com.lianji.te.model.response.Photo
import com.lianji.te.rest.model.request.PhotoRequest
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    @RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<Photo> createPhoto(
            @RequestBody
                    PhotoRequest photoRequest
    ) {
        new ResponseEntity(new Photo(), HttpStatus.CREATED)
    }

    @RequestMapping(path = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<Photo> updatePhoto(
            @RequestBody
                    PhotoRequest photoRequest
    ) {
        new ResponseEntity(new Photo(), HttpStatus.OK)
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<Photo> deletePhoto(
            @PathVariable
                    long id
    ) {
        new ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
