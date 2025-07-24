package com.example.sample_service.repository;

import com.example.sample_service.enums.SampleType;
import com.example.sample_service.models.Sample;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
public class SampleTestRepository {
    @Autowired
    private SampleRepository sampleRepository;

    @Test
    @DisplayName("Debería guardar y recuperar una  lista de muestras")
    void saveAllSamples_ShouldReturnSampleList(){
        //Arrange
        Sample sample1 = new Sample();
        sample1.setSampleType(SampleType.ROCK);
        sample1.setDescription("Descripción válida");
        sample1.setCollectedDate(LocalDate.of(2025, 7, 20));
        sample1.setMeasurementValue(10.5);
        sample1.setIdStudy(1L);

        Sample sample2 = new Sample();
        sample2.setSampleType(SampleType.SOIL);
        sample2.setDescription("Otra muestra válida");
        sample2.setCollectedDate(LocalDate.of(2025, 7, 19));
        sample2.setMeasurementValue(5.0);
        sample2.setIdStudy(2L);

        //Act
        List<Sample> savedSamples = sampleRepository.saveAll(List.of(sample1, sample2));

        // Assert
        Assertions.assertEquals(2, savedSamples.size());
        Assertions.assertNotNull(savedSamples.get(0).getIdSample());
        Assertions.assertEquals(SampleType.ROCK, savedSamples.get(0).getSampleType());
        Assertions.assertEquals("Descripción válida", savedSamples.get(0).getDescription());

        Assertions.assertNotNull(savedSamples.get(1).getIdSample());
        Assertions.assertEquals(SampleType.SOIL, savedSamples.get(1).getSampleType());
        Assertions.assertEquals("Otra muestra válida", savedSamples.get(1).getDescription());

    }


    @Test
    @DisplayName("Encontar una muestra por id")
    void findSampleById_ShouldReturnSample(){
        //Arrange
        Sample sample1 = new Sample();
        sample1.setIdStudy(1L);
        sample1.setDescription("---");
        sample1.setSampleType(SampleType.ROCK);
        sample1.setCollectedDate(LocalDate.of(2025, 2, 4));
        sample1.setMeasurementValue(7.0);

        Sample saved = sampleRepository.save(sample1);

        //Act
        Optional<Sample> found = sampleRepository.findById(saved.getIdSample());


        //Assert
        Assertions.assertNotNull(found.get().getIdSample());
        Assertions.assertEquals(sample1.getIdSample(), found.get().getIdSample());
    }


}
