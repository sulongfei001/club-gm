package com.sevenXnetworks.treasure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Timer;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/11/15 19:30
 * @Version 1.0
 */
@Configuration
public class TimerConfig {
    @Bean
    public Timer timer() {
        return new Timer();
    }
}
