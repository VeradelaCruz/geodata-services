package com.example.sample_service.feingclient;

import com.example.sample_service.dtos.StudyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "study-service", url = "http://localhost:8082")
public interface StudyClient {
    @GetMapping("/study/id/{idStudy}")
    public StudyDTO getStudyById(@PathVariable Long idStudy);
}
