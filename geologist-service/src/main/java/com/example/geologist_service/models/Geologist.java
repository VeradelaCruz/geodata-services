package com.example.geologist_service.models;

import com.example.geologist_service.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "geologists")
public class Geologist {

    @Id
    private String idGeologist;

    @NotBlank(message = "Name can not be empty")
    private String nameGeologist;

    @NotBlank(message = "Name can not be empty")
    private String lastNameGeologist;

    @NotBlank(message = "Specialization can not be empty")
    private String specialization;
    @Email
    @NotBlank(message = "Email con not be empty")
    private String emailGeologist;

    @NotNull(message = "Los años de experiencia son obligatorios")
    @Min(value = 0, message = "La experiencia no puede ser negativa")
    private Double yearsOfExperience;

    private Gender gender;


    public String getIdGeologist() {
        return idGeologist;
    }
    public void setIdGeologist(String idGeologist) {
        this.idGeologist = idGeologist;
    }

    // Getter y Setter para nameGeologist
    public String getNameGeologist() {
        return nameGeologist;
    }
    public void setNameGeologist(String nameGeologist) {
        this.nameGeologist = nameGeologist;
    }

    // Getter y Setter para lastNameGeologist
    public String getLastNameGeologist() {
        return lastNameGeologist;
    }
    public void setLastNameGeologist(String lastNameGeologist) {
        this.lastNameGeologist = lastNameGeologist;
    }

    // Getter y Setter para specialization
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    // Getter y Setter para emailGeologist
    public String getEmailGeologist() {
        return emailGeologist;
    }
    public void setEmailGeologist(String emailGeologist) {
        this.emailGeologist = emailGeologist;
    }

    // Getter y Setter para yearsOfExperience
    public Double getYearsOfExperience() {
        return yearsOfExperience;
    }
    public void setYearsOfExperience(Double yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    // Getter y Setter para gender
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

}


