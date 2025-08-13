package com.example.sample_service.dtos;

import com.example.sample_service.enums.SampleType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchSampleDTO {

    private SampleType sampleType;

    @Size(max = 300, message = "The description must be up to 300 characters.")
    private String description;

    private LocalDate collectedDate;

    @DecimalMin(value = "0.0", inclusive = false, message = "Value must be positive.")
    private Double measurementValue;

    private String idStudy;
}

