package com.example.sample_service.controller;

import com.example.sample_service.dtos.PatchSampleDTO;
import com.example.sample_service.dtos.SampleAndStudyDTO;
import com.example.sample_service.models.Sample;
import com.example.sample_service.service.SampleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sample")
public class SampleController {
    @Autowired
    private SampleService sampleService;

    //Add sample
    @PostMapping("/addSample")
    public List<Sample> addSample(@Valid @RequestBody List<Sample> sampleList) {
        sampleService.createSample(sampleList);
        return sampleList;
    }

    //Get all samples
    @GetMapping("/getAllSamples")
    public List<Sample> getAllSamples(){
        return sampleService.showAllSamples();
    }

    //Get sample by id
    @GetMapping("/id/{idSample}")
    public ResponseEntity<Sample> getSampleById(@PathVariable String idSample) {
        Sample sample = sampleService.showSampleById(idSample);
        return ResponseEntity.ok(sample);
    }


    //Delete sample
    @DeleteMapping("/delete/id/{idSample}")
    public ResponseEntity<String> deleteSample(@PathVariable String idSample){
        sampleService.removeSampleById(idSample);
        return ResponseEntity.ok("Sample with id " + idSample + " has been removed successfully.");
    }

    @PatchMapping("/update/{idSample}")
    public ResponseEntity<String> updateSample(@PathVariable String idSample,
                                               @Valid @RequestBody PatchSampleDTO patchSampleDTO) {
        sampleService.changeSample(idSample, patchSampleDTO);
        return ResponseEntity.ok("Sample updated.");
    }

    @GetMapping("/sorted/byMeasurement")
    public ResponseEntity<?> getSamplesSortedByMeasurement(){
        List<Sample> list= sampleService.showSamplesSortedByMeasurement();
        return ResponseEntity.ok(list);

    }

    @GetMapping("/sorted/byDates")
    public ResponseEntity<?> getSamplesSortedByDates(){
        List<Sample> list= sampleService.showSamplesSortedByDates();
        return ResponseEntity.ok(list);

    }

    @GetMapping("/filter/byCollectableDate/{collectableDate}")
    public ResponseEntity<?> getSamplesFilterByDate(
            @PathVariable("collectableDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate collectableDate) {
        List<Sample> list = sampleService.showSamplesAfterADate(collectableDate);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getSampleAndStudy/{idSample}")
    public ResponseEntity<SampleAndStudyDTO> getSampleAndStudy(@PathVariable String idSample){
        SampleAndStudyDTO sampleAndStudyDTO= sampleService.showSampleWithStudy(idSample);
        return ResponseEntity.ok(sampleAndStudyDTO);
    }

}


