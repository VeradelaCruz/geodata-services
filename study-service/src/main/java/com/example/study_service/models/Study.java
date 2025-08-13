package com.example.study_service.models;

import com.example.study_service.enums.StudyStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "studies")
public class Study {
    @Id
    private String idStudy;

    @NotBlank(message = "This study must have a name.")
    private String title;

    @NotBlank(message = "This study must have a location.")
    private String location;

    @NotNull(message = "This study must have a start date.")
    private LocalDate startDate;

    @NotNull(message = "This study must have an end date.")
    private LocalDate endDate;

    private StudyStatus studyStatus;

    private List<String> geologistIds;

}
