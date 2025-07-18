package com.example.sample_service.dtos;

import com.example.sample_service.models.Sample;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SampleAndStudyDTO {
    private Sample sample;
    private StudyDTO studyDTO;
}
