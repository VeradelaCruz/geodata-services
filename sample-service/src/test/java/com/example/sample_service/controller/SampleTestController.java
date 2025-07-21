package com.example.sample_service.controller;

import com.example.sample_service.dtos.PatchSampleDTO;
import com.example.sample_service.enums.SampleType;
import com.example.sample_service.exeption.ResourceNotFoundException;
import com.example.sample_service.feingclient.StudyClient;
import com.example.sample_service.models.Sample;
import com.example.sample_service.service.SampleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SampleController.class)
public class SampleTestController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudyClient studyClient;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SampleService sampleService;


    @Test
    @DisplayName("Llama al método post para crear una lista de muestras")
    void addSample_ShouldReturnAList() throws Exception {
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

        List<Sample> sampleList = List.of(sample1, sample2);


        Mockito.when(sampleService.createSample(anyList())).thenReturn(sampleList);

        //Act + assert
        mockMvc.perform(post("/sample/addSample")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("Llama al método get para mostrar una lista de todas muestras")
    void getAllSamples_ShouldReturnAList() throws Exception {
        //Arrange
        Sample sample1 = new Sample();

        Sample sample2 = new Sample();

        List<Sample> sampleList = List.of(sample1, sample2);

        Mockito.when(sampleService.showAllSamples()).thenReturn(sampleList);

        //Act + assert
        mockMvc.perform(get("/sample/getAllSamples")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("Llama al metodo get para traer una muestra por id, si existe")
    void getSampleById_ShouldReturnASample() throws Exception {
        //Arrange
        Sample sample1 = new Sample();
        sample1.setIdSample(1L);

        Mockito.when(sampleService.showSampleById(1L)).thenReturn(sample1);

        //Act + assert
        mockMvc.perform(get("/sample/id/{idSample}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idSample").value(1));
    }

    @Test
    @DisplayName("Llama al método get para traer una muestra por id, si NO existe")
    void getSampleById_ShouldReturnAnException() throws Exception {
        //Arrange
        Sample sample1 = new Sample();
        sample1.setIdSample(1L);

        Mockito.when(sampleService.showSampleById(1L))
                .thenThrow(new ResourceNotFoundException("Sample not found"));

        //Act + assert
        mockMvc.perform(get("/sample/id/{idSample}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result ->
                        assertEquals("Sample not found", result.getResolvedException().getMessage()));
    }

    @Test
    @DisplayName("Llama al método delete para eliminar una muestra por id, si existe")
    void deleteSample_ShouldReturnVoid() throws Exception{
        //Arrange:
        Sample sample1 = new Sample();
        sample1.setIdSample(1L);

        doNothing().when(sampleService).removeSampleById(1L);

        //Act + assert
        mockMvc.perform(delete("/sample/delete/id/{idSample}", 1L))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("Sample with id 1 has been removed successfully."));
    }

    @Test
    @DisplayName("Llama al método delete para eliminar una muestra por id, si NO existe")
    void deleteSample_ShouldReturnException() throws Exception{
        //Arrange
        Mockito.doThrow(new ResourceNotFoundException("Sample not found"))
                .when(sampleService).removeSampleById(1L);
        //Act + assert
        mockMvc.perform(delete("/sample/delete/id/{idSample}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Sample not found"));
    }

    @Test
    @DisplayName("Llama al método que actualiza una muestra")
    void updateSample_ShouldReturnOkMessage() throws Exception {
        // Arrange
        Long sampleId = 1L;

        PatchSampleDTO patchSample = new PatchSampleDTO();
        patchSample.setSampleType(SampleType.MINERAL);
        patchSample.setDescription("Otra muestra válida");
        patchSample.setMeasurementValue(5.0);
        patchSample.setIdStudy(1L);

        Sample sampleUpdated = new Sample();
        sampleUpdated.setIdSample(sampleId);
        sampleUpdated.setSampleType(SampleType.MINERAL);
        sampleUpdated.setDescription("Otra muestra válida");
        sampleUpdated.setMeasurementValue(5.0);
        sampleUpdated.setIdStudy(1L);

        Mockito.when(sampleService.changeSample(eq(sampleId), any(PatchSampleDTO.class)))
                .thenReturn(sampleUpdated);

        // Act + Assert
        mockMvc.perform(patch("/sample/update/{idSample}", sampleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(patchSample)))
                .andExpect(status().isOk())
                .andExpect(content().string("Sample updated."));
    }


}
