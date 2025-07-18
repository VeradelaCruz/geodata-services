package com.example.study_service.service;

import com.example.study_service.dtos.PatchStudy;
import com.example.study_service.exception.ResourceNotFoundException;
import com.example.study_service.models.Study;
import com.example.study_service.repository.StudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudyService {
    @Autowired
    private StudyRepository studyRepository;

    //Create study:
    public List<Study> createStudy(List<Study> studyList){
        return studyRepository.saveAll(studyList);
    }

    //Show all studies:
    public List<Study> showAllStudies(){
        return studyRepository.findAll();
    }

    //Show studie by id:
    public Study showStudyById(Long idStudy){
        return studyRepository.findById(idStudy)
                .orElseThrow(()-> new ResourceNotFoundException("Study with id "+ idStudy + " not found."));
    }

    //Delete study:
    public void removeStudy(Long idStudy){
        if(!studyRepository.existsById(idStudy)){
            studyRepository.deleteById(idStudy);
        }
    }

    //Patch study:
    public Study patchStudy(Long idStudy, PatchStudy patchStudy){
        Study studyUpdated= showStudyById(idStudy);
        if(patchStudy.getLocation()!=null){
            studyUpdated.setLocation(patchStudy.getLocation());
        }
        if(patchStudy.getTitle()!=null){
            studyUpdated.setTitle(patchStudy.getTitle());
        }
        if(patchStudy.getStartDate()!=null){
            studyUpdated.setStartDate(patchStudy.getStartDate());
        }
        if(patchStudy.getEndDate()!=null){
            studyUpdated.setEndDate(patchStudy.getEndDate());
        }
        if(patchStudy.getStudyStatus()!=null){
            studyUpdated.setStudyStatus(patchStudy.getStudyStatus());
        }
        return studyRepository.save(studyUpdated);
    }

    //Show studies by location:
    public List<Study> showStudiesByLocation() {
        List<Study> studyList = showAllStudies()
                .stream()
                .sorted(Comparator.comparing(Study::getLocation))
                .collect(Collectors.toList());
        return studyList;
    }

    public List<Study> showBetweenDates(LocalDate minDate, LocalDate maxDate) {
        return showAllStudies()
                .stream()
                .filter(study -> {
                    LocalDate start = study.getStartDate();
                    return start != null &&
                            (start.isEqual(minDate) || start.isAfter(minDate)) &&
                            (start.isEqual(maxDate) || start.isBefore(maxDate));
                })
                .collect(Collectors.toList());
    }




}
