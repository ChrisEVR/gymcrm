package com.epam.gymcrm.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.rds.auth.GetIamAuthTokenRequest;
import com.amazonaws.services.rds.auth.RdsIamAuthTokenGenerator;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.logging.Logger;

@Configuration
public class RDSIAMDataSource {
    private static final Logger logger = Logger.getLogger(RDSIAMDataSource.class.getName());

    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.hikari.max-lifetime}")
    private String maxLifetime;
    @Value("${aws.iam.hostname}")
    private String hostname;
    @Value("${aws.iam.port}")
    private Integer port;
    @Value("${aws.iam.region}")
    private String region;


    @Bean
    @Primary
    public DataSource getDataSource(){
        logger.info("driver:" + driverClassName);
        logger.info("lifetime:" + Integer.parseInt(maxLifetime));

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setMaxLifetime(Integer.parseInt(maxLifetime));
        dataSource.setPassword(generateAuthToken());
        return dataSource;
    }

    public String generateAuthToken(){
        RdsIamAuthTokenGenerator generator = RdsIamAuthTokenGenerator
                .builder()
                .credentials(new DefaultAWSCredentialsProviderChain())
                .region(region)
                .build();

        String authToken = generator.getAuthToken(
                GetIamAuthTokenRequest.builder()
                        .hostname(hostname)
                        .port(port)
                        .userName(username)
                        .build()
        );

        return authToken;
    }
}