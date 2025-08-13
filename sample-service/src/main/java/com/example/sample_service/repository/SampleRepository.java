package com.example.sample_service.repository;

import com.example.sample_service.models.Sample;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SampleRepository extends MongoRepository<Sample, String> {
}
