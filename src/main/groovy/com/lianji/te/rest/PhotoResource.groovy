package com.lianji.te.rest

import com.lianji.te.model.response.Photo
import com.lianji.te.rest.model.request.PhotoRequest
import com.lianji.te.service.PhotoMetadataService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDate
import java.util.stream.Collectors

// GET http://localhost:8080/gallery/photo/v1?taken_date=3012-01-12&album=test
@RestController
@RequestMapping(ResourceConstants.GALLERY_PHOTO_V1)
class PhotoResource {

    private static final Logger log = LoggerFactory.getLogger(getClass())
    private static final UPLOAD_FOLDER = "/tmp"

    // todo
//    @Autowired
    PhotoMetadataService photoMetadataService = new PhotoMetadataService()

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
            @ModelAttribute
                    PhotoRequest photoRequest
    ) {
        log.info("creating photo, request={}", photoRequest)
        try {
            Photo photo = photoMetadataService.getPhoto(
                    photoRequest.name,
                    photoRequest.description,
                    photoRequest.file.inputStream
            )
            log.info("created photo={}", photo)
            saveUploadFiles(Arrays.asList(photoRequest.file))
            new ResponseEntity(photo, HttpStatus.CREATED)
        } catch (IOException ignored) {
            new ResponseEntity<Photo>(HttpStatus.BAD_REQUEST)
        }
    }

    @RequestMapping(path = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<Photo> updatePhoto(
            @RequestBody
                    PhotoRequest photoRequest
    ) {
        log.info("updating photo={}", photoRequest)
        new ResponseEntity(new Photo(), HttpStatus.OK)
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<Photo> deletePhoto(
            @PathVariable
                    long id
    ) {
        new ResponseEntity(HttpStatus.NO_CONTENT)
    }

    private static List<Path> saveUploadFiles(List<MultipartFile> files) throws IOException {
        files.stream()
                .filter { f -> !f.isEmpty() }
                .each { file ->
            byte[] bytes = file.getBytes()
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename())
            Files.write(path, bytes)
        }.collect(Collectors.toList())
    }
}
