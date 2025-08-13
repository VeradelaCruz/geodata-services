package com.example.sample_service.service;

import com.example.sample_service.dtos.PatchSampleDTO;
import com.example.sample_service.dtos.SampleAndStudyDTO;
import com.example.sample_service.dtos.StudyDTO;
import com.example.sample_service.exeption.ResourceNotFoundException;
import com.example.sample_service.feingclient.StudyClient;
import com.example.sample_service.models.Sample;
import com.example.sample_service.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SampleService {
    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private StudyClient studyClient;

    //Create samples
    public List<Sample> createSample(List<Sample> sampleList){
        return sampleRepository.saveAll(sampleList);
    }

    //Show all samples:
    public List<Sample> showAllSamples(){
       return sampleRepository.findAll();
    }

    //Show sample by id:
     public Sample showSampleById(String idSample){
        return sampleRepository.findById(idSample)
                .orElseThrow(()-> new ResourceNotFoundException("Sample with id " + idSample + " not found."));
     }

     //Delete a sample
    public void removeSampleById(String idSample){
       if (!sampleRepository.existsById(idSample)){
           throw  new ResourceNotFoundException("Sample with id " + idSample + " not found.");
       }else {
           sampleRepository.deleteById(idSample);
       }
    }

    //Update a sample:
    public Sample changeSample(String idSample, PatchSampleDTO patchSampleDTO){
        Sample sampleUpdated= showSampleById(idSample);
        if(patchSampleDTO.getSampleType()!= null){
            sampleUpdated.setSampleType(patchSampleDTO.getSampleType());
        }
        if(patchSampleDTO.getDescription()!= null){
            sampleUpdated.setDescription(patchSampleDTO.getDescription());
        }
        if(patchSampleDTO.getSampleType()!= null){
            sampleUpdated.setSampleType(patchSampleDTO.getSampleType());
        }
        if(patchSampleDTO.getCollectedDate()!= null){
            sampleUpdated.setCollectedDate(patchSampleDTO.getCollectedDate());
        }
        if(patchSampleDTO.getMeasurementValue()!= null){
            sampleUpdated.setMeasurementValue(patchSampleDTO.getMeasurementValue());
        }
        if(patchSampleDTO.getIdStudy()!= null){
            sampleUpdated.setIdStudy(patchSampleDTO.getIdStudy());
        }
        return sampleRepository.save(sampleUpdated);
    }

    //Show samples sorted by mesure:
    public List<Sample> showSamplesSortedByMeasurement(){
        List<Sample> sampleList= showAllSamples()
                .stream()
                .sorted(Comparator.comparing(Sample::getMeasurementValue).reversed())
                .collect(Collectors.toList());
        return sampleList;
    }

    //Show samples by date:
    public List<Sample> showSamplesSortedByDates(){
        List<Sample> sampleList= showAllSamples()
                .stream()
                .sorted(Comparator.comparing(Sample::getCollectedDate).reversed())
                .collect(Collectors.toList());
        return sampleList;
    }

    //Show samples collected after a date
    public List<Sample> showSamplesAfterADate(LocalDate collectedDate){
        List<Sample> samples= showAllSamples()
                .stream()
                .filter(sample -> sample.getCollectedDate().isAfter(collectedDate))
                .collect(Collectors.toList());
        return samples;
    }

    //Show sample with studies:
    public SampleAndStudyDTO showSampleWithStudy(String idSample){
        Sample sample= showSampleById(idSample);
        StudyDTO study= studyClient.getStudyById(sample.getIdStudy());

        return new SampleAndStudyDTO(sample, study);

    }







}
