package io.codelex.flightplanner.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${flight-planner.database.username}")
    private String username;

    @Value("${flight-planner.database.password}")
    private String password;

    @Value("${flight-planner.database.url}")
    private String url;

    @Bean
    @ConditionalOnProperty(prefix = "flight-planner", name = "connection-type", havingValue = "in-memory")
    public DataSource getDatabaseDataSourceInMemory() {
        return DataSourceBuilder.create()
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:mem:inmemoryH2DB")
                .username("martins")
                .password("")
                .build();

    }

    @Bean
    @ConditionalOnProperty(prefix = "flight-planner", name = "connection-type", havingValue = "postgresql")
    public DataSource getDatabaseDataSourcePostgreSQL() {

        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url(url)
                .username(username)
                .password(password)
                .build();

    }


}