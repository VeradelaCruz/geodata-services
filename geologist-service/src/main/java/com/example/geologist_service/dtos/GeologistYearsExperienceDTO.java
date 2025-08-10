package com.example.geologist_service.dtos;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeologistYearsExperienceDTO {
    private String idGeologist;
    private String nameGeologist;
    private String lastNameGeologist;
    private Double yearsOfExperience;


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

    // Getter y Setter para yearsOfExperience
    public Double getYearsOfExperience() {
        return yearsOfExperience;
    }
    public void setYearsOfExperience(Double yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
