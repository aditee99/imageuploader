package com.aws.imageuploader.com.aws.imageuploader.service;

import java.io.IOException;

public class ImageUploadException extends RuntimeException {
    public ImageUploadException(String message) {
        super(message);
    }
}
