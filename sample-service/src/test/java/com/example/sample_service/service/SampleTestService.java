package com.example.sample_service.service;

import com.example.sample_service.dtos.PatchSampleDTO;
import com.example.sample_service.dtos.SampleAndStudyDTO;
import com.example.sample_service.dtos.StudyDTO;
import com.example.sample_service.enums.SampleType;
import com.example.sample_service.exeption.ResourceNotFoundException;
import com.example.sample_service.feingclient.StudyClient;
import com.example.sample_service.models.Sample;
import com.example.sample_service.repository.SampleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class SampleTestService {
    @Mock
    private SampleRepository sampleRepository;

    @Mock
    private StudyClient studyClient;

    @InjectMocks
    private SampleService sampleService;



    @Test
    @DisplayName("Debe guardar una lista de muestras y devolverlas correctamente")
    void createSample_ShouldReturnAListOfSamples(){
        //Arrange
        Sample sample1= new Sample();
        sample1.setDescription("---");
        sample1.setSampleType(SampleType.ROCK);
        sample1.setCollectedDate(LocalDate.of(2025, 2, 4));
        sample1.setMeasurementValue(7.0);

        Sample sample2= new Sample();
        sample2.setDescription("---");
        sample2.setSampleType(SampleType.MINERAL);
        sample2.setCollectedDate(LocalDate.of(2025, 10, 20));
        sample2.setMeasurementValue(9.0);

        List<Sample> sampleList= List.of(sample1, sample2);

        Mockito.when(sampleRepository.saveAll(sampleList)).thenReturn(sampleList);

        //Act
        List<Sample> result = sampleService.createSample(sampleList);

        //Asser:
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(sampleList.get(0).getSampleType(), result.get(0).getSampleType());
        Assertions.assertEquals(sampleList.get(1).getSampleType(), result.get(1).getSampleType());

        //Verify
        Mockito.verify(sampleRepository).saveAll(sampleList);
    }

    @Test
    @DisplayName("Debe devolver una lista con todas las muestras")
    void showAllSamples_ShouldReturnAList(){
        //Arrange
        Sample sample1= new Sample();
        sample1.setSampleType(SampleType.ROCK);

        Sample sample2= new Sample();
        sample2.setSampleType(SampleType.ROCK);

        List<Sample> sampleList= List.of(sample1, sample2);

        Mockito.when(sampleRepository.findAll()).thenReturn(sampleList);

        //Act
        List<Sample> result = sampleService.showAllSamples();

        //Assert
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(sampleList.get(0).getSampleType(), result.get(0).getSampleType());
        Assertions.assertEquals(sampleList.get(1).getSampleType(), result.get(1).getSampleType());

        //Verify
        Mockito.verify(sampleRepository).findAll();
    }

    @Test
    @DisplayName("Debería devolver una muestra por id, si existe")
    void showSampleById_ShouldReturnSample(){
        //Arrange
        Sample sample1= new Sample();
        sample1.setIdSample(1L);

        Mockito.when(sampleRepository.findById(1L)).thenReturn(Optional.of(sample1));

        //Act
        Sample result= sampleService.showSampleById(1L);

        //Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getIdSample());

        //Verify
        Mockito.verify(sampleRepository).findById(1L);
    }

    @Test
    @DisplayName("Debería devolver una muestra por id, si NO existe")
    void showSampleById_ShouldReturnException(){
        //Arrange
        Long id = 1L;
        Mockito.when(sampleRepository.findById(id)).thenReturn(Optional.empty());

        // Act + Assert
        ResourceNotFoundException exception = Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> sampleService.showSampleById(id)
        );

        Assertions.assertEquals("Sample with id 1 not found.", exception.getMessage());

        // Verify
        Mockito.verify(sampleRepository).findById(id);
    }

    @Test
    @DisplayName("Debería eliminar una muestra, si existe")
    void removeSample_ShouldReturnVoid(){
        //Arrange
        Sample sample1= new Sample();
        sample1.setIdSample(1L);

        Mockito.when(sampleRepository.existsById(1L)).thenReturn(true);

        //Act
        sampleService.removeSampleById(1L);

        //Verify
        Mockito.verify(sampleRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Debería eliminar una muestra, si NO existe")
    void removeSample_ShouldReturnException(){
        //Arrange
        Long id= 1L;
        Mockito.when(sampleRepository.existsById(1L)).thenReturn(false);

        //Act
        ResourceNotFoundException exception= Assertions.assertThrows(ResourceNotFoundException.class,
                ()->sampleService.removeSampleById(1L));
        //Assert
        Assertions.assertEquals("Sample with id " + 1L + " not found.", exception.getMessage());
    }

    @Test
    @DisplayName("Debería devolver una muestra modificada")
    void changeSample_ShouldReturnSample(){
        //Arrange
        Sample sample1= new Sample();
        sample1.setDescription("---");
        sample1.setSampleType(SampleType.ROCK);
        sample1.setCollectedDate(LocalDate.of(2025, 2, 4));
        sample1.setMeasurementValue(7.0);

        PatchSampleDTO patchSample= new PatchSampleDTO();
        patchSample.setDescription("---");
        patchSample.setSampleType(SampleType.MINERAL);
        patchSample.setCollectedDate(LocalDate.of(2025, 2, 4));
        patchSample.setMeasurementValue(5.0);

        Mockito.when(sampleRepository.findById(sample1.getIdSample())).thenReturn(Optional.of(sample1));
        Mockito.when(sampleRepository.save(sample1)).thenReturn(sample1);

        //Act
        Sample resultSample= sampleService.changeSample(sample1.getIdSample(), patchSample);

        //Assert
        Assertions.assertEquals(sample1.getSampleType(), resultSample.getSampleType());
        Assertions.assertEquals(sample1.getMeasurementValue(), resultSample.getMeasurementValue());

        //Verify
        Mockito.verify(sampleRepository).findById(sample1.getIdSample());
        Mockito.verify(sampleRepository).save(sample1);
    }

    @Test
    @DisplayName("Debería devolver una lista de muestras ordenadas por tamaño")
    void showSamplesSortedByMeasurement_ShouldReturnSampleList(){
        //Arrange
        Sample sample1= new Sample();
        sample1.setDescription("---");
        sample1.setSampleType(SampleType.ROCK);
        sample1.setCollectedDate(LocalDate.of(2025, 2, 4));
        sample1.setMeasurementValue(7.0);

        Sample sample2= new Sample();
        sample2.setDescription("---");
        sample2.setSampleType(SampleType.MINERAL);
        sample2.setCollectedDate(LocalDate.of(2025, 10, 20));
        sample2.setMeasurementValue(9.0);

        List<Sample> sampleList= List.of(sample1, sample2);

        Mockito.when(sampleRepository.findAll()).thenReturn(sampleList);

        //Act
        List<Sample> resultSamples= sampleService.showSamplesSortedByMeasurement();

        //Assert
        assertThat(resultSamples)
                .extracting(Sample::getMeasurementValue)
                .containsExactly(9.0, 7.0);


        //Verify
        Mockito.verify(sampleRepository).findAll();
    }

    @Test
    @DisplayName("Debería devolver una lista de muestras ordenadas por fechas")
    void showSamplesSortedByDates_ShouldReturnAList(){
        //Arrange
        Sample sample1= new Sample();
        sample1.setDescription("---");
        sample1.setSampleType(SampleType.ROCK);
        sample1.setCollectedDate(LocalDate.of(2025, 2, 4));
        sample1.setMeasurementValue(7.0);

        Sample sample2= new Sample();
        sample2.setDescription("---");
        sample2.setSampleType(SampleType.MINERAL);
        sample2.setCollectedDate(LocalDate.of(2025, 10, 20));
        sample2.setMeasurementValue(9.0);

        List<Sample> sampleList= List.of(sample1, sample2);

        Mockito.when(sampleRepository.findAll()).thenReturn(sampleList);

        //Act
        List<Sample> resultSamples= sampleService.showSamplesSortedByDates();

        //Assert
        assertThat(resultSamples)
                .extracting(Sample::getCollectedDate)
                .containsExactly(LocalDate.of(2025, 10, 20),
                        LocalDate.of(2025, 2, 4));

        //Verify
        Mockito.verify(sampleRepository).findAll();
    }

    @Test
    @DisplayName("Debería devolver una lista de muestras filtrada por una fecha")
    void showSamplesAfterADate_ShouldReturnAList(){
        //Arrange
        Sample sample1= new Sample();
        sample1.setDescription("---");
        sample1.setSampleType(SampleType.ROCK);
        sample1.setCollectedDate(LocalDate.of(2025, 2, 4));
        sample1.setMeasurementValue(7.0);

        Sample sample2= new Sample();
        sample2.setDescription("---");
        sample2.setSampleType(SampleType.MINERAL);
        sample2.setCollectedDate(LocalDate.of(2025, 10, 20));
        sample2.setMeasurementValue(9.0);

        LocalDate collectableDate=LocalDate.of(2025, 01, 01);

        List<Sample> sampleList= List.of(sample1, sample2);

        Mockito.when(sampleRepository.findAll()).thenReturn(sampleList);

        //Act
        List<Sample> samples= sampleService.showSamplesAfterADate(collectableDate);

        //Assert
        Assertions.assertEquals(sampleList.size(), samples.size());
        assertThat(samples)
                .extracting(Sample::getCollectedDate)
                .containsExactly(LocalDate.of(2025, 2, 4),
                        LocalDate.of(2025, 10, 20));

        //Verify
        Mockito.verify(sampleRepository).findAll();

    }

    @Test
    @DisplayName("Debería devolver una muestra con su estudio relacionado")
    void showSampleWithStudy_ShouldReturnSampleAndStudyDTO(){
        //Arrange
        Sample sample1= new Sample();
        sample1.setIdSample(99L);
        sample1.setSampleType(SampleType.ROCK);
        sample1.setIdStudy(1L);

        StudyDTO study= new StudyDTO();
        study.setIdStudy(1L);
        study.setLocation("Neuquen");
        study.setTitle("Recoleccion de muestras");
        study.setStudyStatus("On going");
        study.setGeologistIds(new ArrayList<Long>(List.of(1L,2L)));

        SampleAndStudyDTO sampleAndStudy= new SampleAndStudyDTO();
        sampleAndStudy.setSample(sample1);
        sampleAndStudy.setStudyDTO(study);

        Mockito.when(sampleRepository.findById(99L)).thenReturn(Optional.of(sample1));
        Mockito.when(studyClient.getStudyById(1L)).thenReturn(study);

        //Act
        SampleAndStudyDTO sampleAndStudyResult = sampleService.showSampleWithStudy(99L);

        //Assert
        Assertions.assertEquals(sampleAndStudy.getSample(), sampleAndStudyResult.getSample());
        Assertions.assertEquals(sampleAndStudy.getStudyDTO(), sampleAndStudyResult.getStudyDTO());
    }
}
