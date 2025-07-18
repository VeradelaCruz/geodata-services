package com.example.study_service.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeologistDTO {
    private String nameGeologist;
    private String lastNameGeologist;
    private String specialization;
}
