package com.example.geologist_service.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoTestConfig {

    @Bean
    public MongoClient mongoClient() {
        // Flapdoodle levanta en puerto dinámico y Spring lo detecta
        return MongoClients.create("mongodb://localhost:27017/testdb");
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "testdb");
    }
}