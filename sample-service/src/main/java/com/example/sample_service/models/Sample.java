package com.example.sample_service.models;

import com.example.sample_service.enums.SampleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Data
@Getter @Setter
@Table(name = "samples")
public class Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sample")
    private Long idSample;

    @Enumerated(EnumType.STRING)
    @Column(name = "sample_type")
    private SampleType sampleType;

    @NotBlank(message = "A description must be provided. ")
    @Size(max = 300, message = "The description must be up to 300 characters.")
    private String description;

    @Column(name = "collected_date")
    private LocalDate collectedDate;

    @DecimalMin(value = "0.0", inclusive = false, message = "Value must be positive.")
    @Column(name = "measurement_value")
    private Double measurementValue;


    @Column(name = "id_study")
    private Long idStudy;
}
