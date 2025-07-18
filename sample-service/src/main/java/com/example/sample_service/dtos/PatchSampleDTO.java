package com.example.sample_service.dtos;

import com.example.sample_service.enums.SampleType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchSampleDTO {

    @Enumerated(EnumType.STRING)
    private SampleType sampleType;

    @Size(max = 300, message = "The description must be up to 300 characters.")
    private String description;

    private LocalDate collectedDate;

    @DecimalMin(value = "0.0", inclusive = false, message = "Value must be positive.")
    private Double measurementValue;

    private Long idStudy;
}

