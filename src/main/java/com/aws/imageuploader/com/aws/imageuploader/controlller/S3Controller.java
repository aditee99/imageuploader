package com.aws.imageuploader.com.aws.imageuploader.controlller;

import com.aws.imageuploader.com.aws.imageuploader.service.ImageUploader;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/s3")
public class S3Controller {
    private ImageUploader uploader;

    public S3Controller(ImageUploader uploader){
        this.uploader = uploader;
    }
    //upload image
    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile file){
        return ResponseEntity.ok(uploader.uploadImage(file));
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllImages() {
        List<String> imageUrls = uploader.getAllImages();
        return ResponseEntity.ok(imageUrls);
    }
}
