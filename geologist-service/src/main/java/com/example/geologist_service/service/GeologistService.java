package com.example.geologist_service.service;

import com.example.geologist_service.dtos.GeologistYearsExperienceDTO;
import com.example.geologist_service.dtos.PatchGeologist;
import com.example.geologist_service.exeption.ResourceNotFoundException;
import com.example.geologist_service.mapper.GeologistMapper;
import com.example.geologist_service.models.Geologist;
import com.example.geologist_service.repository.GeologistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class GeologistService {

    @Autowired
    private GeologistRepository geologistRepository;
    @Autowired
    private GeologistMapper geologistMapper;

    //Create geologist:
    public List<Geologist> createGeologist(List<Geologist> geologistList){
        return geologistRepository.saveAll(geologistList);
    }

    //Show all geologists:
    public List<Geologist> showAllGeologists(){
        return geologistRepository.findAll();
    }

    //Show geologist by id:
    public Geologist showGeologistById(Long idGeologist) {
        return geologistRepository.findById(idGeologist)
                .orElseThrow(() -> new ResourceNotFoundException("Geologist not found with id " + idGeologist));
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

    //Partial update with patch:
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
    public  List<GeologistYearsExperienceDTO> findAllGeologistByExperience(
            Predicate<GeologistYearsExperienceDTO> geologistYearsExperienceDTOPredicate){
        return showAllGeologists().stream()
                .map(geologistMapper::toDto)
                .filter(g -> g.getYearsOfExperience() > 5)
                .collect(Collectors.toList());
    }






}
