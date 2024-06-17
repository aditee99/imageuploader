package com.aws.imageuploader.com.aws.imageuploader.exceptions;

import com.aws.imageuploader.com.aws.imageuploader.service.ImageUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ImageUploadException.class)
    public ResponseEntity<CustomResponse> handleImageUploadException(ImageUploadException imageUploadException) {
        CustomResponse response = CustomResponse.builder()
                .message(imageUploadException.getMessage())
                .success(false)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
