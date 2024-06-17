package com.aws.imageuploader.com.aws.imageuploader.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.aws.imageuploader.com.aws.imageuploader.entity.ImageMetadata;
import com.aws.imageuploader.com.aws.imageuploader.repository.ImageMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class S3ImageUploader implements ImageUploader {

    @Autowired
    private AmazonS3 client;

    @Autowired
    private ImageMetadataRepository imageMetadataRepository;

    @Value("${app.s3.bucket}")
    private String bucketName;

    @Override
    public String uploadImage(MultipartFile image) throws ImageUploadException {
        String actualFileName = image.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + actualFileName.substring(actualFileName.lastIndexOf("."));
        ObjectMetadata metaData = new ObjectMetadata();
        metaData.setContentLength(image.getSize());
        try {
            PutObjectResult putObjectResult = client.putObject(new PutObjectRequest(bucketName, fileName, image.getInputStream(), metaData));
            String s3Url = client.getUrl(bucketName, fileName).toString();

            // Save metadata to the database
            ImageMetadata imageMetadata = new ImageMetadata(fileName, s3Url);
            imageMetadataRepository.save(imageMetadata);

            return fileName;
        } catch (IOException e) {
            throw new ImageUploadException("error in uploading image " + e.getMessage());
        }
    }

    @Override
    public List<String> getAllImages() {
        ListObjectsV2Request request = new ListObjectsV2Request().withBucketName(bucketName);
        ListObjectsV2Result result;

        List<String> imageUrls = new ArrayList<>();

        do {
            result = client.listObjectsV2(request);

            for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                imageUrls.add(client.getUrl(bucketName, objectSummary.getKey()).toString());
            }

            request.setContinuationToken(result.getNextContinuationToken());
        } while (result.isTruncated());

        return imageUrls;
    }
}
