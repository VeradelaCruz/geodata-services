package com.example.geologist_service.controller;

import com.example.geologist_service.dtos.GeologistDTO;
import com.example.geologist_service.dtos.GeologistYearsExperienceDTO;
import com.example.geologist_service.dtos.PatchGeologist;
import com.example.geologist_service.exeption.GlobalExceptionHandler;
import com.example.geologist_service.exeption.ResourceNotFoundException;
import com.example.geologist_service.mapper.GeologistMapper;
import com.example.geologist_service.models.Geologist;
import com.example.geologist_service.service.GeologistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;



import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GeologistController.class)
@ActiveProfiles("test")
@Import(GlobalExceptionHandler.class)
public class GeologistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GeologistService geologistService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GeologistMapper geologistMapper;


    @Test
    @DisplayName("Test para agregar un geólogo a la base de datos")
    void addGeologist_ShouldReturnEntity() throws Exception {
        // Arrange
        Geologist g1 = new Geologist();
        g1.setIdGeologist(1L);
        g1.setLastNameGeologist("Smith");

        Geologist g2 = new Geologist();
        g2.setIdGeologist(2L);
        g2.setLastNameGeologist("Jones");

        List<Geologist> list = List.of(g1, g2);

        when(geologistService.createGeologist(anyList())).thenReturn(list);

        // Act & Assert
        mockMvc.perform(post("/geologist/addGeologist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(list)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].lastNameGeologist").value("Smith"));
    }


    @Test
    @DisplayName("Test para ver todos los geólogos")
    void getAllGeologists_ShouldReturnAList() throws Exception {
        // Arrange
        Geologist g1 = new Geologist();
        g1.setIdGeologist(1L);
        g1.setLastNameGeologist("Smith");

        Geologist g2 = new Geologist();
        g2.setIdGeologist(2L);
        g2.setLastNameGeologist("Jones");

        List<Geologist> list = List.of(g1, g2);

        when(geologistService.showAllGeologists()).thenReturn(list);

        // Act + Assert
        mockMvc.perform(get("/geologist/getAllGeologists") // <- get(), no "/getAllGeologists"
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].lastNameGeologist").value("Smith"))
                .andExpect(jsonPath("$[1].lastNameGeologist").value("Jones"));
    }


    @Test
    @DisplayName("Test que devuelve un geólogo por id si lo encuentra")
    void getGeologistById_ShouldReturnGeologist() throws Exception {
        //Arrange
        Geologist geologist= new Geologist();
        geologist.setIdGeologist(1L);

        when(geologistService.showGeologistById(1L)).thenReturn(geologist);

        //Act + assert:
        mockMvc.perform(get("/geologist/getGeologistById/{idGeologist}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idGeologist").value(1));

    }

    @Test
    @DisplayName("Test que devuelve un geólogo por id si NO encuentra")
    void getGeologistById_ShouldThrowException() throws Exception {

        when(geologistService.showGeologistById(1L))
                .thenThrow(new ResourceNotFoundException("Geologist not found"));

        //Act + assert:
        mockMvc.perform(get("/geologist/getGeologistById/{idGeologist}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(result ->
                assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result ->
                        assertEquals("Geologist not found", result.getResolvedException().getMessage()));
    }

    @Test
    @DisplayName("Test que devuelve un geologo actualizado")
    void  updateGeologist_ShouldReturnGeologistUpdated() throws Exception{
        //Arrange
        Long id = 1L;
        PatchGeologist patchGeologist = new PatchGeologist();
        Geologist geologist= new Geologist();

        when(geologistService.patchGeologist(id, patchGeologist)).thenReturn(geologist);

        // Convertir a JSON
        String jsonContent = new ObjectMapper().writeValueAsString(patchGeologist);

        // Act + Assert
        mockMvc.perform(patch("/geologist/updateGeologist/{idGeologist}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(content().string("Geologist updated."));
    }

    @Test
    @DisplayName("Test que devuelve un geólogo por años de experiencia")
    void getGeologistsByYearsOfExperience_ShouldReturnListOfGeologists() throws Exception{
        //Arrange
        GeologistDTO g1 = new GeologistDTO();
        g1.setIdGeologist(1L);
        g1.setLastNameGeologist("Smith");
        g1.setYearsOfExperience(5.0);

        GeologistDTO g2 = new GeologistDTO();
        g2.setIdGeologist(2L);
        g2.setLastNameGeologist("Jones");
        g2.setYearsOfExperience(7.0);
        List<GeologistDTO> list = List.of(g1, g2);

        GeologistYearsExperienceDTO geoDTO1= new GeologistYearsExperienceDTO();
        geoDTO1.setIdGeologist(1L);
        geoDTO1.setLastNameGeologist("Smith");
        geoDTO1.setYearsOfExperience(5.0);

        GeologistYearsExperienceDTO geoDTO2= new GeologistYearsExperienceDTO();
        geoDTO2.setIdGeologist(2L);
        geoDTO2.setLastNameGeologist("Jones");
        geoDTO2.setYearsOfExperience(7.0);

        List<GeologistYearsExperienceDTO> listDTO = List.of(geoDTO1, geoDTO2);

        Double minYears= 4.0;

        when(geologistService.findAllGeologistByExperience(minYears)).thenReturn(listDTO);


        //Act+ Assert
        mockMvc.perform(get("/geologist/getGeologistsByYearsOfExperience")
                        .param("minYears", String.valueOf(minYears)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));

    }


}
