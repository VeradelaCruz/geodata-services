package com.example.geologist_service.dtos;

import com.example.geologist_service.enums.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PatchGeologist {
    private String nameGeologist;
    private String lastNameGeologist;  // <--- agregar este campo
    private String specialization;
    @Email
    private String emailGeologist;
    @Min(value = 0, message = "La experiencia no puede ser negativa")
    private Double yearsOfExperience;
    private Gender gender;

    public PatchGeologist() {
    }

    public PatchGeologist(String nameGeologist, String lastNameGeologist, String specialization, String emailGeologist,
                          Double yearsOfExperience, Gender gender) {
        this.nameGeologist = nameGeologist;
        this.lastNameGeologist = lastNameGeologist;
        this.specialization = specialization;
        this.emailGeologist = emailGeologist;
        this.yearsOfExperience = yearsOfExperience;
        this.gender = gender;
    }

    public String getNameGeologist() { return nameGeologist; }
    public void setNameGeologist(String nameGeologist) { this.nameGeologist = nameGeologist; }

    public String getLastNameGeologist() { return lastNameGeologist; }
    public void setLastNameGeologist(String lastNameGeologist) { this.lastNameGeologist = lastNameGeologist; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getEmailGeologist() { return emailGeologist; }
    public void setEmailGeologist(String emailGeologist) { this.emailGeologist = emailGeologist; }

    public Double getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(Double yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

}

