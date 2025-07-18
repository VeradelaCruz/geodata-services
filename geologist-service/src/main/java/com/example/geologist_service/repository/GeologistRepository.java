package com.example.geologist_service.repository;

import com.example.geologist_service.models.Geologist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeologistRepository extends JpaRepository<Geologist, Long> {
}
