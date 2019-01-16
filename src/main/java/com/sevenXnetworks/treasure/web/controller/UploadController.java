package com.sevenXnetworks.treasure.web.controller;

import com.sevenXnetworks.treasure.exception.ParameterException;
import com.sevenXnetworks.treasure.model.CustomerErrorConstants;
import com.sevenXnetworks.treasure.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/image_uploading")
public class UploadController extends BaseController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object> uploadPhoto(@RequestParam("file") MultipartFile file) throws Exception {
        if (file != null) {
            String tmpFileName = uploadService.uploadPhoto(file);
            Map map = new HashMap();
            map.put("tmpFileName", tmpFileName);
            return ResponseEntity.ok(map);
        }
        throw new ParameterException(CustomerErrorConstants.UPLOAD_FILE_NULL);
    }
}
