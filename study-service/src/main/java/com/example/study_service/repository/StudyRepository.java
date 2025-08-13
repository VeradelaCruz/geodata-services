package com.example.study_service.repository;

import com.example.study_service.models.Study;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudyRepository extends MongoRepository<Study, String> {
}
