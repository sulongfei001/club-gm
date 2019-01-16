package com.sevenXnetworks.treasure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //todo
        http.requestMatchers()
                .antMatchers("/dictionaries/**")
                .antMatchers("/image_uploading/**")
                .antMatchers("/bar/**")
                .antMatchers("/activity/**")
                .antMatchers("/goods/**")
                .antMatchers("/order/**")


                .and()
                .authorizeRequests()
                .antMatchers("/dictionaries/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/image_uploading/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/bar/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/activity/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/goods/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/order/**").hasAnyRole("USER", "ADMIN")


        ;

    }

}
