package com.example.sample_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudyDTO {
    private Long idStudy;
    private String title;
    private String location;
    private String studyStatus;
    private List<Long> geologistIds;

}
