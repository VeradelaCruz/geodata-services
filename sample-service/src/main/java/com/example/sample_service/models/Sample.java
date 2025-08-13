package com.example.sample_service.models;

import com.example.sample_service.enums.SampleType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "samples")
public class Sample {
    @Id
    private String idSample;

    private SampleType sampleType;

    @NotBlank(message = "A description must be provided. ")
    @Size(max = 300, message = "The description must be up to 300 characters.")
    private String description;

    private LocalDate collectedDate;

    @DecimalMin(value = "0.0", inclusive = false, message = "Value must be positive.")
    private Double measurementValue;

    private String idStudy;
}
