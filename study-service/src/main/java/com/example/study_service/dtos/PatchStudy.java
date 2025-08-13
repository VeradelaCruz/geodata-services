package com.example.study_service.dtos;

import com.example.study_service.enums.StudyStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchStudy {
    private String title;
    private String location;

    private LocalDate startDate;

    private LocalDate endDate;

    private StudyStatus studyStatus;

}
