package com.sevenXnetworks.treasure.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    String uploadPhoto(MultipartFile file) throws IOException, Exception;
}
