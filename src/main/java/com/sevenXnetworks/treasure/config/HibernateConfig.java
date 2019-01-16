package com.sevenXnetworks.treasure.config;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
public class HibernateConfig {


    //配置sessionFactory
    @Bean
    public LocalSessionFactoryBean sessionFactory(
            @Qualifier("dataSource") DataSource dataSource,
            @Value("${spring.jpa.show-sql}") String showSql,
            @Value("${spring.jpa.hibernate.ddl-auto}") String ddlAuto,
            @Value("${spring.jpa.properties.hibernate.dialect}") String dialect,
            @Value("${spring.jpa.properties.hibernate.format_sql}") String formatSql,
            @Value("${spring.jpa.properties.hibernate.cache.use_second_level_cache}") String useSecondLevelCache,
            @Value("${spring.jpa.properties.hibernate.cache.use_query_cache}") String useQueryCache,
            @Value("${spring.jpa.properties.hibernate.cache.provider_class}") String providerClass,
            @Value("${spring.jpa.properties.hibernate.cache.region.factory_class}") String factoryClass,
            @Value("${spring.jpa.properties.hibernate.jdbc.fetch_size}") String fetchSize,
            @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}") String batchSize,
            @Value("${spring.jpa.properties.hibernate.connection.autocommit}") String autocommit,
            @Value("${spring.jpa.properties.hibernate.connection.release_mode}") String releaseMode,
            @Value("${spring.jpa.properties.hibernate.current_session_context_class}") String currentSessionContextClass,

            @Value("${spring.jpa.properties.hibernate.packagesToScan}") String packageScan
    ) {

        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan(packageScan);

        Properties properties = new Properties();

        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.show_sql", showSql);
        properties.setProperty("hibernate.format_sql", formatSql);
        properties.setProperty("hibernate.hbm2ddl.auto", ddlAuto);
        properties.setProperty("hibernate.cache.use_second_level_cache", useSecondLevelCache);
        properties.setProperty("hibernate.cache.use_query_cache", useQueryCache);
        properties.setProperty("hibernate.cache.provider_class", providerClass);
        properties.setProperty("hibernate.cache.region.factory_class", factoryClass);
        properties.setProperty("hibernate.jdbc.fetch_size", fetchSize);
        properties.setProperty("hibernate.jdbc.batch_size", batchSize);
        properties.setProperty("hibernate.connection.autocommit", autocommit);
        properties.setProperty("hibernate.connection.release_mode", releaseMode);
        properties.setProperty("hibernate.current_session_context_class", currentSessionContextClass);

        localSessionFactoryBean.setHibernateProperties(properties);

        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;
    }
}