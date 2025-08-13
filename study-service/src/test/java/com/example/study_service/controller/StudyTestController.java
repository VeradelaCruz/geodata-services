package com.example.study_service.controller;

import com.example.study_service.dtos.PatchStudy;
import com.example.study_service.enums.StudyStatus;
import com.example.study_service.exception.ResourceNotFoundException;
import com.example.study_service.feingClient.GeologistClient;
import com.example.study_service.models.Study;
import com.example.study_service.service.StudyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
@ActiveProfiles("test")
public class StudyTestController {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudyService studyService;

    @MockBean
    private GeologistClient geologistClient;

    @Test
    @DisplayName("Debería devolver una lista de estudios creados")
    void createStudy_ShouldReturnListStudy() throws  Exception{
        //Arrange
        Study study1= new Study();
        study1.setTitle("Búsqueda zona 1");
        study1.setStartDate(LocalDate.of(2025,3,10));
        study1.setEndDate(LocalDate.of(2025,10, 20));
        study1.setLocation("Zona 1");
        study1.setStudyStatus(StudyStatus.ONGOING);

        List<String> idsStudy1= List.of("1L","2L");
        study1.setGeologistIds(idsStudy1);

        Study study2 = new Study();
        study2.setTitle("Búsqueda zona 1");
        study2.setStartDate(LocalDate.of(2025,1,9));
        study2.setEndDate(LocalDate.of(2025,4, 30));
        study2.setLocation("Zona 1");
        study2.setStudyStatus(StudyStatus.COMPLETED);

        List<String> idsStudy2 = List.of("1L","2L");
        study2.setGeologistIds(idsStudy2);

        List<Study> studyList= List.of(study1, study2);

        when(studyService.createStudy(anyList())).thenReturn(studyList);

        //Act + Assert
        mockMvc.perform(post("/study/addStudies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studyList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("Deberia devolver una lista con todos los estudios")
    void getALlStudies_ShouldReturnAList()throws Exception{
        //Arrange
        Study study1= new Study();
        study1.setTitle("Búsqueda zona 1");

        Study study2 = new Study();
        study2.setTitle("Búsqueda zona 1");

        List<Study> studyList= List.of(study1, study2);

        when(studyService.showAllStudies()).thenReturn(studyList);

        //Act + arrange
        mockMvc.perform(get("/study/getAllStudies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value(study1.getTitle()))
                .andExpect(jsonPath("$[1].title").value(study2.getTitle()));
    }

    @Test
    @DisplayName("Debería devolver un estudio por id, si existe")
    void getStudyById_ShouldReturnStudy() throws Exception{
        //Arrange
        String id= "1L";
        Study study= new Study();
        study.setIdStudy(id);

        when(studyService.showStudyById(id)).thenReturn(study);

        //Act + assert
        mockMvc.perform(get("/study/id/{idStudy}", "1L")
                        .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idStudy").isNotEmpty())
                .andExpect(jsonPath("$.idStudy").value("1L"));
    }

    @Test
    @DisplayName("Debería devolver un estudio por id, si NO existe")
    void getStudyById_ShouldReturnException() throws Exception{
        //Arrange
        String id= "1L";

        when(studyService.showStudyById("1L"))
                .thenThrow(new ResourceNotFoundException("Study not found."));

        //Act + assert
        mockMvc.perform(get("/study/id/{idStudy}", "1L"))
                .andExpect(status().isNotFound())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result ->
                        assertEquals("Study not found.", result.getResolvedException().getMessage()));
    }

    @Test
    @DisplayName("Debería devolver un void al eliminar un estudio")
    void deleteStudy_ShouldReturnVoid() throws Exception{
        //Arrange
        String id= "1L";
        Study study= new Study();
        study.setIdStudy(id);

        doNothing().when(studyService).removeStudy(id);

        mockMvc.perform(delete("/study/delete/{idStudy}","1L")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Study with id 1L has been removed successfully."));

    }

    @Test
    @DisplayName("Debería devolver un estudio actualizado")
    void updateStudy_ShouldReturnAStudy() throws Exception{
        //Arrange
        Study studyUpdated = new Study();
        studyUpdated.setIdStudy("2L");
        studyUpdated.setTitle("Búsqueda zona 1");
        studyUpdated.setStartDate(LocalDate.of(2025,3,10));
        studyUpdated.setEndDate(LocalDate.of(2025,10, 20));
        studyUpdated.setLocation("Zona 1");
        studyUpdated.setStudyStatus(StudyStatus.ONGOING);

        PatchStudy patchStudy=new PatchStudy();
        patchStudy.setTitle("Búsqueda zona 2");
        patchStudy.setStartDate(LocalDate.of(2025,3,10));
        patchStudy.setEndDate(LocalDate.of(2025,10, 20));
        patchStudy.setLocation("Zona 2");
        patchStudy.setStudyStatus(StudyStatus.ONGOING);

        Mockito.when(studyService.patchStudy(studyUpdated.getIdStudy(), patchStudy))
                .thenReturn(studyUpdated);

        //Act + assert
        mockMvc.perform(patch("/study/update/{idStudy}", "1L")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patchStudy)))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Debería devolver una lista de estudios ordenadas por localizacion")
    void getStudiesByLocation_ShouldReturnList() throws Exception{
        //Arrange
        Study study1= new Study();
        study1.setTitle("Búsqueda zona 1");
        study1.setLocation("Zona 1");

        Study study2= new Study();
        study2.setTitle("Búsqueda zona 2");
        study2.setLocation("Zona 2");

        List<Study> studyList= List.of(study1, study2);

        Mockito.when(studyService.showStudiesByLocation()).thenReturn(studyList);

        //Act + Assert
        mockMvc.perform(get("/study/sorted/byLocation")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }
}
