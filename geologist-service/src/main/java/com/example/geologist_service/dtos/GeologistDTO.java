package com.example.geologist_service.dtos;

import com.example.geologist_service.enums.Gender;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeologistDTO {

    private String idGeologist;

    private String nameGeologist;

    private String lastNameGeologist;

    @NotBlank(message = "Specialization can not be empty")
    private String specialization;

    private String emailGeologist;

    @Min(value = 0, message = "La experiencia no puede ser negativa")
    private Double yearsOfExperience;

    private Gender gender;

    // Getters y Setters

    public String getIdGeologist() {
        return idGeologist;
    }

    public void setIdGeologist(String idGeologist) {
        this.idGeologist = idGeologist;
    }

    public String getNameGeologist() {
        return nameGeologist;
    }

    public void setNameGeologist(String nameGeologist) {
        this.nameGeologist = nameGeologist;
    }

    public String getLastNameGeologist() {
        return lastNameGeologist;
    }

    public void setLastNameGeologist(String lastNameGeologist) {
        this.lastNameGeologist = lastNameGeologist;
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
