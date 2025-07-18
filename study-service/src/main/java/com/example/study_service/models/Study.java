package com.example.study_service.models;

import com.example.study_service.enums.StudyStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Getter @Setter
@Table(name = "studies")
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_study")
    private Long idStudy;

    @NotBlank(message = "This study must have a name.")
    private String title;

    @NotBlank(message = "This study must have a location.")
    private String location;

    @NotNull(message = "This study must have a start date.")
    @Column(name = "start_date")
    private LocalDate startDate;

    @NotNull(message = "This study must have an end date.")
    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "study_status")
    private StudyStatus studyStatus;

    @ElementCollection
    @CollectionTable(name = "study_geologists", joinColumns = @JoinColumn(name = "id_study"))
    @Column(name = "id_geologist")
    private List<Long> geologistIds;

}
