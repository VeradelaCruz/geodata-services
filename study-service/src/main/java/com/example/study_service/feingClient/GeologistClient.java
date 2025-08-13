package com.example.study_service.feingClient;

import com.example.study_service.dtos.GeologistDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "geologist-service", url = "http://localhost:8080")
public interface GeologistClient {
    @PostMapping("/geologist/by-ids")
    List<GeologistDTO> getGeologistsByIds(@RequestBody List<String> ids);

}
