package com.aws.imageuploader.com.aws.imageuploader.repository;


import com.aws.imageuploader.com.aws.imageuploader.entity.ImageMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageMetadataRepository extends JpaRepository<ImageMetadata, Long> {
}

