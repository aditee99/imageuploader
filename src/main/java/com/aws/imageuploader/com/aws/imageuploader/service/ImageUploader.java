package com.aws.imageuploader.com.aws.imageuploader.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageUploader {
    String uploadImage(MultipartFile image) throws ImageUploadException;
    List<String> getAllImages();

}
