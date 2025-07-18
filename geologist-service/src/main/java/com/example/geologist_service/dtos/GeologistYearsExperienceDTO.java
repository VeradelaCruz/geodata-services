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

    // Constructor
    public GeologistYearsExperienceDTO(Geologist g) {
        this.idGeologist = g.getIdGeologist();
        this.nameGeologist = g.getNameGeologist();
        this.lastNameGeologist = g.getLastNameGeologist();
        this.yearsOfExperience = g.getYearsOfExperience();
    }
}
