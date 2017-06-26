package com.lianji.te.rest

import com.lianji.te.model.request.UploadModel
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors

@RestController
class RestUploadController {
    Logger logger = LoggerFactory.getLogger(getClass())

    private static final String UPLOAD_FOLDER = "/tmp/"

    @PostMapping("api/upload")
    ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile uploadFile) {
        logger.debug("single file uploading")
        if (uploadFile.isEmpty()) {
            new ResponseEntity("Please select a file", HttpStatus.OK)
        } else {
            try {
                saveUploadFiles(Arrays.asList(uploadFile))
                new ResponseEntity("Successfully uploaded: " + uploadFile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK)
            } catch (IOException ignored) {
                new ResponseEntity(HttpStatus.BAD_REQUEST)
            }
        }
    }

    def getFileName = { f -> f.getOriginalFilename() }

    @PostMapping("api/upload/multi")
    ResponseEntity<?> uploadFiles(
            @RequestParam("name") String name,
            @RequestParam("files") MultipartFile[] uploadFiles
    ) {
        logger.debug("multi file uploading with extra field name={}", name)
        String uploadedFileName = Arrays
                .stream(uploadFiles)
                .map(getFileName)
                .filter { s -> !StringUtils.isEmpty(s) }
                .collect(Collectors.joining(", "))
        if (StringUtils.isEmpty(uploadedFileName)) {
            new ResponseEntity("Please select a file", HttpStatus.OK)
        } else {
            try {
                saveUploadFiles(Arrays.asList(uploadFiles))
                new ResponseEntity("Successfully uploaded: " + uploadFiles.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK)
            } catch (IOException ignored) {
                new ResponseEntity(HttpStatus.BAD_REQUEST)
            }
        }
    }

    // map html form to a Model
    @PostMapping("api/upload/multi/model")
    ResponseEntity<?> uploadFileModel(@ModelAttribute UploadModel model) {
        logger.debug("multiple file upload! with UploadModel=", model)
        try {
            saveUploadFiles(Arrays.asList(model.files))
            new ResponseEntity("Successfully uploaded with UploadModel", HttpStatus.OK)
        } catch (IOException ignored) {
            new ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    private static void saveUploadFiles(List<MultipartFile> files) throws IOException {
        files.stream().filter { f -> !f.isEmpty() }.each { file ->
            byte[] bytes = file.getBytes()
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename())
            Files.write(path, bytes)
        }
    }
}
