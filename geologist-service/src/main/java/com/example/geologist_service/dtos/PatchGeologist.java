package com.example.geologist_service.dtos;

import com.example.geologist_service.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchGeologist {
    private String nameGeologist;
    private String specialization;
    @Email

    private String emailGeologist;
    @Min(value = 0, message = "La experiencia no puede ser negativa")
    private Double yearsOfExperience;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
