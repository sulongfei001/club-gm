package com.sevenXnetworks.treasure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.requestMatchers().antMatchers("/oauth/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated();
    }

    @Autowired
    private UserDetailsService jdbcUserService;

    @Bean
    public UserDetailsService jdbcUserService(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        JdbcUserDetailsManagerConfigurer jdbcUserDetailsManagerConfigurer = new JdbcUserDetailsManagerConfigurer();
        jdbcUserDetailsManagerConfigurer
                .dataSource(dataSource)
                .usersByUsernameQuery("select user_name, user_password, is_enable from security_user where user_name=?")
                .authoritiesByUsernameQuery("select u.user_name, r.role_name from security_user as u, security_role as r where u.user_name=? and u.role_id = r.id");

        return jdbcUserDetailsManagerConfigurer.getUserDetailsService();
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        ReflectionSaltSource saltSource = new ReflectionSaltSource();
        saltSource.setUserPropertyToUse("username");

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(new Md5PasswordEncoder());
        authProvider.setSaltSource(saltSource);
        authProvider.setUserDetailsService(jdbcUserService);

        auth.authenticationProvider(authProvider);
    }
}