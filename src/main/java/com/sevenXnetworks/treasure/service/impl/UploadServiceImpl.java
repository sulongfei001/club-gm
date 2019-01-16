package com.sevenXnetworks.treasure.service.impl;

import com.sevenXnetworks.treasure.config.ConfigProperties;
import com.sevenXnetworks.treasure.exception.BusinessException;
import com.sevenXnetworks.treasure.model.CustomerErrorConstants;
import com.sevenXnetworks.treasure.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class UploadServiceImpl implements UploadService {
    @Autowired
    private ConfigProperties properties;

    @Override
    public String uploadPhoto(MultipartFile file) {
        File tempDir = new File(properties.getTempDir());
        File tempFile = new File(tempDir, UUID.randomUUID() + "_" + file.getOriginalFilename());
        try {
            if (!tempDir.exists()) {
                tempDir.mkdirs();
            }
            file.transferTo(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(CustomerErrorConstants.UPLOAD_FILE_FAIL);
        }
        return tempFile.getName();
    }
}
