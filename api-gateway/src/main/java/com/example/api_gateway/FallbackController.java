package com.example.api_gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/fallback/geologists")
    public ResponseEntity<String> geologistFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Geologist Service is temporarily unavailable.");
    }

    @GetMapping("/fallback/studies")
    public ResponseEntity<String> studyFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Study Service is temporarily unavailable.");
    }

    @GetMapping("/fallback/samples")
    public ResponseEntity<String> sampleFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Sample Service is temporarily unavailable.");
    }

}

