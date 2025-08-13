package com.example.study_service.controllers;

import com.example.study_service.dtos.PatchStudy;
import com.example.study_service.dtos.StudyAndGeologistDTO;
import com.example.study_service.models.Study;
import com.example.study_service.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/study")
public class StudyController {
    @Autowired
    private StudyService studyService;

    @PostMapping("/addStudies")
    public List<Study> addStudies(@RequestBody List<Study> listStudies){
        List<Study> list= studyService.createStudy(listStudies);
        return list;
    }

    @GetMapping("/getAllStudies")
    public List<Study> getAllStudies(){
        List<Study> list=studyService.showAllStudies();
        return list;
    }

    @GetMapping("/id/{idStudy}")
    public Study getStudyById(@PathVariable String idStudy){
        return studyService.showStudyById(idStudy);
    }

    @DeleteMapping("/delete/{idStudy}")
    public ResponseEntity<?> deleteStudy(@PathVariable String idStudy){
        studyService.removeStudy(idStudy);
        return ResponseEntity.ok("Study with id "+ idStudy+ " has been removed successfully.");
    }

    @PatchMapping("/update/{idStudy}")
    public ResponseEntity<?> updateStudy(@PathVariable String idStudy, @RequestBody PatchStudy patchStudy){
        studyService.patchStudy(idStudy, patchStudy);
        return ResponseEntity.ok("Study has been updated successfully.");
    }

    @GetMapping("/sorted/byLocation")
    public List<Study> getStudiesSortedByLocation(){
        return  studyService.showStudiesByLocation();
    }

    @GetMapping("/filter/betweenDates")
    public List<Study> getStudiesBetweenDates(@RequestParam LocalDate minDate,
                                              @RequestParam LocalDate maxDate) {
        return studyService.showBetweenDates(minDate, maxDate);
    }


    @GetMapping("/{id}/with-geologists")
    public ResponseEntity<StudyAndGeologistDTO> getStudyWithGeologists(
            @PathVariable("id") String idStudy) {
        StudyAndGeologistDTO result = studyService.showStudyAndGeologist(idStudy);
        return ResponseEntity.ok(result);
    }






}
