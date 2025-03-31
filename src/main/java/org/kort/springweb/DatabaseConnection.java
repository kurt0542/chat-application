package org.kort.springweb;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnection {
    private JdbcTemplate jdbcTemplate;

    public DatabaseConnection(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void DatabaseConnection() {
        try{
            jdbcTemplate.execute("SELECT 1");
            System.out.println("Database connection established");
        }catch(Exception e){
            System.out.println("Database connection failed" + e.getMessage());
        }
    }
}
