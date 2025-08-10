package com.example.geologist_service.repository;

import com.example.geologist_service.models.Geologist;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GeologistRepository extends MongoRepository<Geologist, String> {
}
