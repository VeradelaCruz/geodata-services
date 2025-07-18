package com.example.geologist_service.models;

import com.example.geologist_service.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "geologists")
public class Geologist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_geologist")
    private Long idGeologist;

    @NotBlank(message = "Name can not be empty")
    @Column(name = "name_geologist")
    private String nameGeologist;

    @NotBlank(message = "Name can not be empty")
    @Column(name = "last_name_geologist")
    private String lastNameGeologist;

    @NotBlank(message = "Specialization can not be empty")
    private String specialization;
    @Email
    @NotBlank(message = "Email con not be empty")
    @Column(name = "email_geologist", unique = true, nullable = false)
    private String emailGeologist;

    @NotNull(message = "Los años de experiencia son obligatorios")
    @Min(value = 0, message = "La experiencia no puede ser negativa")
    @Column(name = "years_of_experience")
    private Double yearsOfExperience;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Long getIdGeologist() {
        return idGeologist;
    }

    public void setIdGeologist(Long idGeologist) {
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


