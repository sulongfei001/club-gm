package com.sevenXnetworks.treasure.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/10/25 17:32
 * @Version 1.0
 */
@Component
@PropertySource("classpath:config/application-config.properties")
@Data
public class ConfigProperties {
    @Value(("${upload.addr}"))
    private String uploadAddr;
    @Value("${temp.file.dir}")
    private String tempDir;
    @Value("${activity.file.dir}")
    private String activityDir;
    @Value("${bar.file.dir}")
    private String barDir;
}
