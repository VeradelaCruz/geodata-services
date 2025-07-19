package com.example.geologist_service.service;

import com.example.geologist_service.dtos.GeologistYearsExperienceDTO;
import com.example.geologist_service.dtos.PatchGeologist;
import com.example.geologist_service.exeption.ResourceNotFoundException;
import com.example.geologist_service.mapper.GeologistMapper;
import com.example.geologist_service.models.Geologist;
import com.example.geologist_service.repository.GeologistRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class GeologistServiceTest {

    @Mock
    private GeologistRepository geologistRepository;

    @Mock
    private GeologistMapper geologistMapper;

    @InjectMocks
    private GeologistService geologistService;

    @Test
    void testShowGeologistById_WhenFound(){
        //Arrange
        Geologist geologist = new Geologist();
        geologist.setIdGeologist(1L);

        Mockito.when(geologistRepository.findById(1L)).thenReturn(Optional.of(geologist));

        //Act
        Geologist result = geologistService.showGeologistById(1L);

        //Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getIdGeologist());
    }

    @Test
    void  testShowGeologistById_ShouldThrowException(){
        //Arrange
        Geologist geologist = new Geologist();
        geologist.setIdGeologist(1L);

        Mockito.when(geologistRepository.findById(1L))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            geologistService.showGeologistById(1L);
        });
    }

    @Test
    void showGeologistSortedByLastName_WhenFound(){
        //Arrange: una lista NO ordenada
        Geologist g1 = new Geologist();
        g1.setLastNameGeologist("Wilson");

        Geologist g2 = new Geologist();
        g2.setLastNameGeologist("Deen");

        List<Geologist> unsortedList = new ArrayList<>();
        unsortedList.add(g1);
        unsortedList.add(g2);

        Mockito.when(geologistRepository.findAll()).thenReturn(unsortedList);

        //Act
        List<Geologist> resultList= geologistService.showGeologistSortedByLastName();

        //Assert: verificamos que la lista fue ordenada
        Assertions.assertEquals(2, resultList.size());
        Assertions.assertEquals("Deen", resultList.get(0).getLastNameGeologist());
        Assertions.assertEquals("Wilson", resultList.get(1).getLastNameGeologist());
    }

    @Test
    void  removeGeologistById_ShouldReturnVoid(){
        //Arrange:
        Long id =1L;

        Mockito.when(geologistRepository.existsById(1L)).thenReturn(true);

        //Act
        geologistService.removeGeologistById(1L);

        //Assert
        Mockito.verify(geologistRepository).deleteById(id);

    }

    @Test
    void patchGeologist_ShouldReturnUpdatedGeologist(){
        //Arrange
        Geologist geologist= new Geologist();
        geologist.setIdGeologist(1L);
        geologist.setEmailGeologist("example@gmail.com");

        PatchGeologist patchGeologist= new PatchGeologist();
        patchGeologist.setEmailGeologist("example99@gmail.com");

        Mockito.when(geologistRepository.findById(1L)).thenReturn(Optional.of(geologist));
        Mockito.when(geologistRepository.save(Mockito.any(Geologist.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        //Act
        Geologist updatedPatch= geologistService.patchGeologist(1L, patchGeologist);

        //Assert
        Assertions.assertEquals(1L, updatedPatch.getIdGeologist());
        Assertions.assertEquals("example99@gmail.com", updatedPatch.getEmailGeologist());

        //Very
        Mockito.verify(geologistRepository).findById(1L);
        Mockito.verify(geologistRepository).save(Mockito.any(Geologist.class));

    }

    @Test
    void findAllGeologistByExperience_ShouldReturnFilteredGeologistList() {
        // Arrange
        Geologist g1 = new Geologist();
        g1.setLastNameGeologist("Wilson");

        Geologist g2 = new Geologist();
        g2.setLastNameGeologist("Deen");

        List<Geologist> geologistList = List.of(g1, g2);

        GeologistYearsExperienceDTO dto1 = new GeologistYearsExperienceDTO();
        dto1.setLastNameGeologist("Wilson");
        dto1.setYearsOfExperience(6.0);

        GeologistYearsExperienceDTO dto2 = new GeologistYearsExperienceDTO();
        dto2.setLastNameGeologist("Deen");
        dto2.setYearsOfExperience(3.0);

        Mockito.when(geologistRepository.findAll()).thenReturn(geologistList);
        Mockito.when(geologistMapper.toDto(g1)).thenReturn(dto1);
        Mockito.when(geologistMapper.toDto(g2)).thenReturn(dto2);

        Double minYears = 5.0;

        // Act
        List<GeologistYearsExperienceDTO> result = geologistService.findAllGeologistByExperience(minYears);

        // Assert
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Wilson", result.get(0).getLastNameGeologist());
    }

}
