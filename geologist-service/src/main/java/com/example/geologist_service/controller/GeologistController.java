package com.example.geologist_service.controller;

import com.example.geologist_service.dtos.GeologistYearsExperienceDTO;
import com.example.geologist_service.dtos.PatchGeologist;
import com.example.geologist_service.exeption.ResourceNotFoundException;
import com.example.geologist_service.models.Geologist;
import com.example.geologist_service.service.GeologistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.DoubleBuffer;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/geologist")
public class GeologistController {

    @Autowired
    private GeologistService geologistService;

    //Add geologist:
    @PostMapping("/addGeologist")
    public List<Geologist> addGeologist(@RequestBody List<Geologist> geologists){
        List savedList= geologistService.createGeologist(geologists);
        return savedList;
    }

    //Get all geologists:
    @GetMapping("/getAllGeologists")
    public List<Geologist> getAllGeologists (){
        return geologistService.showAllGeologists();
    }

    //Get geologist by id
    @GetMapping("/getGeologistById/{idGeologist}")
    public ResponseEntity<?> getGeologistById(@PathVariable Long idGeologist) {
            Geologist geologist = geologistService.showGeologistById(idGeologist);
            return ResponseEntity.ok(geologist);
    }

    //Get geologists sorted by last name:
    @GetMapping("/getGeologistSortedByLastName")
    public List<Geologist> getGeologistSortedByLastName(){
        return geologistService.showGeologistSortedByLastName();
    }

    //Delete a geologist
    @DeleteMapping("/deleteGeologistById/{idGeologist}")
    public ResponseEntity<String> deleteGeologistById(@PathVariable Long idGeologist){
        try{
            geologistService.removeGeologistById(idGeologist);
            return ResponseEntity.ok().body("Geologist with Id: " + idGeologist + " has been removed successfully. ");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while trying to delete the geologist.");
        }

    }

    @PatchMapping("/updateGeologist/{idGeologist}")
    public ResponseEntity<?> updateGeologist (@PathVariable Long idGeologist,
                                              @Valid @RequestBody PatchGeologist patchGeologist){
        try {
            geologistService.patchGeologist(idGeologist,patchGeologist);
            return ResponseEntity.ok().body("Geologist updated.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getCause());
        }
    }


    @GetMapping("/getGeologistsByYearsOfExperience")
    public List<GeologistYearsExperienceDTO> getGeologistsByYearsOfExperience(
            @RequestParam Double minYears){
        Predicate<GeologistYearsExperienceDTO> predicate =
                dto -> dto.getYearsOfExperience() > minYears;
        return geologistService.findAllGeologistByExperience(predicate);

    }




}
