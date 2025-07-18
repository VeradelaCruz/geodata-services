package com.example.sample_service.repository;

import com.example.sample_service.models.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long> {
}
