package com.sevenXnetworks.treasure.utils;

import com.sevenXnetworks.treasure.exception.BusinessException;
import com.sevenXnetworks.treasure.model.CustomerErrorConstants;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtils {
    public static Logger log = Logger.getLogger(FileUtils.class);

    public static void copyFile_(String tempDirStr, String fileName, String newDirStr) {
        if (fileName == null) {
            return;
        }
        File tempFile = new File(tempDirStr + File.separator + fileName);
        if (tempFile == null) {
            throw new BusinessException(CustomerErrorConstants.FILE_NOT_EXIST);
        }
        File newDir = new File(newDirStr);
        File newFile = new File(newDirStr + File.separator + fileName);
        if (!newDir.exists()) {
            newDir.mkdirs();
        }
        try {
            Files.copy(tempFile.toPath(), newFile.toPath());
            //log.info("文件移动成功！文件名：" + fileName + ",起始路径：" + tempDirStr + ",目标路径：" + newDirStr);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("文件移动失败！文件名：" + fileName + ",起始路径：" + tempDirStr + ",目标路径：" + newDirStr);
            throw new BusinessException(CustomerErrorConstants.FILE_MOVE_FILE);
        }
    }


    public static void moveFile(String tempDirStr, String fileName, String newDirStr) {
        if (fileName == null) {
            return;
        }
        File tempFile = new File(tempDirStr + File.separator + fileName);
        if (tempFile == null) {
            throw new BusinessException(CustomerErrorConstants.FILE_NOT_EXIST);
        }
        File newDir = new File(newDirStr);
        File newFile = new File(newDirStr + File.separator + fileName);
        if (!newDir.exists()) {
            newDir.mkdirs();
        }
        if (!tempFile.renameTo(newFile)) {
            log.info("文件移动失败！文件名：" + fileName + ",起始路径：" + tempDirStr + ",目标路径：" + newDirStr);
            throw new BusinessException(CustomerErrorConstants.FILE_MOVE_FILE);
        }
    }

}
