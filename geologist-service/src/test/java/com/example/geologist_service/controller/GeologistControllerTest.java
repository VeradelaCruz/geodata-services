package com.example.geologist_service.controller;

import com.example.geologist_service.dtos.GeologistDTO;
import com.example.geologist_service.dtos.GeologistYearsExperienceDTO;
import com.example.geologist_service.dtos.PatchGeologist;
import com.example.geologist_service.exeption.ResourceNotFoundException;
import com.example.geologist_service.models.Geologist;
import com.example.geologist_service.service.GeologistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;



import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class GeologistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GeologistService geologistService;

    @Autowired
    private ObjectMapper objectMapper;

    private Geologist g1;
    private Geologist g2;
    private List<Geologist> geologistList;

    private GeologistDTO dto1;
    private GeologistDTO dto2;
    private List<GeologistDTO> dtoList;

    private GeologistYearsExperienceDTO expDto1;
    private GeologistYearsExperienceDTO expDto2;
    private List<GeologistYearsExperienceDTO> expDtoList;

    private PatchGeologist patchGeologist;

    @BeforeEach
    void setUp() {
        // Model
        g1 = new Geologist();
        g1.setIdGeologist("1L");
        g1.setLastNameGeologist("Smith");

        g2 = new Geologist();
        g2.setIdGeologist("2L");
        g2.setLastNameGeologist("Jones");

        geologistList = List.of(g1, g2);

        // DTO
        dto1 = new GeologistDTO();
        dto1.setIdGeologist("1L");
        dto1.setLastNameGeologist("Smith");
        dto1.setYearsOfExperience(5.0);

        dto2 = new GeologistDTO();
        dto2.setIdGeologist("2L");
        dto2.setLastNameGeologist("Jones");
        dto2.setYearsOfExperience(7.0);

        dtoList = List.of(dto1, dto2);

        // DTO experiencia
        expDto1 = new GeologistYearsExperienceDTO();
        expDto1.setIdGeologist("1L");
        expDto1.setLastNameGeologist("Smith");
        expDto1.setYearsOfExperience(5.0);

        expDto2 = new GeologistYearsExperienceDTO();
        expDto2.setIdGeologist("2L");
        expDto2.setLastNameGeologist("Jones");
        expDto2.setYearsOfExperience(7.0);

        expDtoList = List.of(expDto1, expDto2);

        // Patch DTO
        patchGeologist = new PatchGeologist();
    }

    @Test
    @DisplayName("Agregar geólogos (async)")
    void addGeologist_ShouldReturnEntity() throws Exception {
        when(geologistService.createGeologist(anyList()))
                .thenReturn(CompletableFuture.completedFuture(geologistList));

        var mvcResult = mockMvc.perform(post("/geologist/geologists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(geologistList)))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].lastNameGeologist").value("Smith"));
    }

    @Test
    @DisplayName("Listar todos los geólogos")
    void getAllGeologists_ShouldReturnAList() throws Exception {
        when(geologistService.showAllGeologists()).thenReturn(geologistList);

        mockMvc.perform(get("/geologist/getAllGeologists")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2)) // <- sin Hamcrest
                .andExpect(jsonPath("$[0].lastNameGeologist").value("Smith"))
                .andExpect(jsonPath("$[1].lastNameGeologist").value("Jones"));
    }

    @Test
    @DisplayName("Buscar por id (encontrado)")
    void getGeologistById_ShouldReturnGeologist() throws Exception {
        when(geologistService.showGeologistById("1L")).thenReturn(g1);

        mockMvc.perform(get("/geologist/getGeologistById/{idGeologist}", "1L")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idGeologist").value("1L"));
    }

    @Test
    @DisplayName("Buscar por id (no encontrado)")
    void getGeologistById_ShouldThrowException() throws Exception {
        when(geologistService.showGeologistById("1L"))
                .thenThrow(new ResourceNotFoundException("Geologist not found"));

        mockMvc.perform(get("/geologist/getGeologistById/{idGeologist}", "1L"))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("Geologist not found",
                        result.getResolvedException().getMessage()));
    }

    @Test
    @DisplayName("Actualizar geólogo (PATCH)")
    void updateGeologist_ShouldReturnGeologistUpdated() throws Exception {
        when(geologistService.patchGeologist("1L", patchGeologist)).thenReturn(g1);

        mockMvc.perform(patch("/geologist/updateGeologist/{idGeologist}", "1L")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patchGeologist)))
                .andExpect(status().isOk())
                .andExpect(content().string("Geologist updated."));
    }

    @Test
    @DisplayName("Filtrar por años de experiencia")
    void getGeologistsByYearsOfExperience_ShouldReturnListOfGeologists() throws Exception {
        Double minYears = 4.0;
        when(geologistService.findAllGeologistByExperience(minYears)).thenReturn(expDtoList);

        mockMvc.perform(get("/geologist/getGeologistsByYearsOfExperience")
                        .param("minYears", String.valueOf(minYears)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2)); // <- sin Hamcrest
    }
}