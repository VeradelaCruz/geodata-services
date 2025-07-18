package com.example.geologist_service.dtos;

import com.example.geologist_service.models.Geologist;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeologistYearsExperienceDTO {
    private Long idGeologist;
    private String nameGeologist;
    private String lastNameGeologist;
    private Double yearsOfExperience;

    public GeologistYearsExperienceDTO() {
    }

    public GeologistYearsExperienceDTO(Long idGeologist, String nameGeologist, String lastNameGeologist, Double yearsOfExperience) {
        this.idGeologist = idGeologist;
        this.nameGeologist = nameGeologist;
        this.lastNameGeologist = lastNameGeologist;
        this.yearsOfExperience = yearsOfExperience;
    }

    // Constructor que recibe una entidad Geologist
    public GeologistYearsExperienceDTO(Geologist g) {
        this.idGeologist = g.getIdGeologist();
        this.nameGeologist = g.getNameGeologist();
        this.lastNameGeologist = g.getLastNameGeologist();
        this.yearsOfExperience = g.getYearsOfExperience();
    }

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

    public Double getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Double yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
