package com.example.study_service.dtos;

import com.example.study_service.models.Study;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyAndGeologistDTO {
    private Study study;
    private List<GeologistDTO> geologist;
}
