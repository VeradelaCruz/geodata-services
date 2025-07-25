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

    public PatchGeologist() {
    }

    public PatchGeologist(String nameGeologist, String specialization, String emailGeologist,
                          Double yearsOfExperience, Gender gender) {
        this.nameGeologist = nameGeologist;
        this.specialization = specialization;
        this.emailGeologist = emailGeologist;
        this.yearsOfExperience = yearsOfExperience;
        this.gender = gender;
    }

    public String getNameGeologist() {
        return nameGeologist;
    }

    public void setNameGeologist(String nameGeologist) {
        this.nameGeologist = nameGeologist;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEmailGeologist() {
        return emailGeologist;
    }

    public void setEmailGeologist(String emailGeologist) {
        this.emailGeologist = emailGeologist;
    }

    public Double getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Double yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
