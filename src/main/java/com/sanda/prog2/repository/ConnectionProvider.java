package com.sanda.prog2.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConnectionProvider {
    @Bean
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            System.getenv("DB_URL"),
            System.getenv("DB_USERNAME"),
            System.getenv("DB_PASSWORD")
        );
    }
}