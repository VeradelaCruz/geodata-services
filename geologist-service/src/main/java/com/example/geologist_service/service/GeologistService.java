package com.example.geologist_service.service;

import com.example.geologist_service.dtos.GeologistYearsExperienceDTO;
import com.example.geologist_service.dtos.PatchGeologist;
import com.example.geologist_service.exeption.ResourceNotFoundException;
import com.example.geologist_service.mapper.GeologistMapper;
import com.example.geologist_service.models.Geologist;
import com.example.geologist_service.repository.GeologistRepository;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
@Async
public class GeologistService {

    @Autowired
    private GeologistRepository geologistRepository;
    @Autowired
    private GeologistMapper geologistMapper;

    //Create geologist:
    //Aplicando métodos asíncronos
    @Retry(name = "geologistretry", fallbackMethod = "fallbackCreateGeologist")
    @Bulkhead(name = "sampleBulkhead", type = Bulkhead.Type.SEMAPHORE)
    @TimeLimiter(name = "geologistTimeLimiter")
    @Async
    public CompletableFuture<List<Geologist>> createGeologist(List<Geologist> geologistList){
        List<Geologist> saved = geologistRepository.saveAll(geologistList);
        return CompletableFuture.completedFuture(saved);
    }

    public CompletableFuture<List<Geologist>> fallbackCreateGeologist(List<Geologist> geologistList, Throwable t){
        // Lógica fallback, por ejemplo retornar lista vacía o cached data
        return CompletableFuture.completedFuture(Collections.emptyList());
    }

    //Show all geologists:
    public List<Geologist> showAllGeologists(){
        return geologistRepository.findAll();
    }

    //Show geologist by id:
    public Geologist showGeologistById(Long idGeologist) {
        return geologistRepository.findById(idGeologist)
                .orElseThrow(() -> new ResourceNotFoundException("Geologist not found"));
    }

    //Show geologists by id:
    public List<Geologist> showGeologistsById( List<Long> ids){
        return geologistRepository.findAllById(ids);
    }

    //Show geologist sorted by last name:
    public List<Geologist> showGeologistSortedByLastName(){
        List<Geologist> geologists= showAllGeologists();
        geologists.sort(Comparator.comparing(Geologist::getLastNameGeologist));
        return geologists;
    }

    //Remove a Geologist:
    public void removeGeologistById(Long idGeologist){
        if (!geologistRepository.existsById(idGeologist)){
            throw new ResourceNotFoundException("Geologist not found with id " + idGeologist);
        }
        geologistRepository.deleteById(idGeologist);
    }

    //Partial update:
    public Geologist patchGeologist(Long idGeologist, PatchGeologist patchGeologist){
        Geologist geologist= showGeologistById(idGeologist);

        if(patchGeologist.getEmailGeologist() != null){
            geologist.setEmailGeologist(patchGeologist.getEmailGeologist());
        }

        if(patchGeologist.getSpecialization() != null){
            geologist.setSpecialization(patchGeologist.getSpecialization());
        }

        if(patchGeologist.getYearsOfExperience() != null){
            geologist.setYearsOfExperience(patchGeologist.getYearsOfExperience());
        }

        if(patchGeologist.getGender() != null){
            geologist.setGender(patchGeologist.getGender());
        }

        return  geologistRepository.save(geologist);
    }


    //Filter geologist by years of experience:
    public List<GeologistYearsExperienceDTO> findAllGeologistByExperience(Double minYears) {
        return showAllGeologists().stream()
                .map(geologistMapper::toDto)
                .filter(g -> g.getYearsOfExperience() > minYears)
                .collect(Collectors.toList());
    }








}
