package com.example.geologist_service.repository;


import com.example.geologist_service.config.MongoTestConfig;
import com.example.geologist_service.enums.Gender;
import com.example.geologist_service.models.Geologist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
@ActiveProfiles("test")
@Import(MongoTestConfig.class)
class GeologistRepositoryTest {

    @Autowired
    private GeologistRepository geologistRepository;

    private Geologist g1;
    private Geologist g2;

    @BeforeEach
    void setUp(){
        geologistRepository.deleteAll();

        g1 = new Geologist();
        g1.setLastNameGeologist("Smith");
        g1.setNameGeologist("John");
        g1.setSpecialization("Mineralogy");
        g1.setEmailGeologist("john.smith@example.com");
        g1.setYearsOfExperience(5.0);
        g1.setGender(Gender.MALE);

        g2 = new Geologist();
        g2.setLastNameGeologist("Jones");
        g2.setNameGeologist("Alice");
        g2.setSpecialization("Petrology");
        g2.setEmailGeologist("alice.jones@example.com");
        g2.setYearsOfExperience(7.0);
        g2.setGender(Gender.FEMALE);

        geologistRepository.saveAll(List.of(g1, g2));
    }

    @Test
    @DisplayName("Crear geólogos")
    void createGeologist_ShouldReturnListOfGeologist() {
        List<Geologist> savedList = geologistRepository.findAll(Sort.by("lastNameGeologist"));

        assertEquals(2, savedList.size());
        assertEquals("Jones", savedList.get(0).getLastNameGeologist());
        assertEquals("Smith", savedList.get(1).getLastNameGeologist());
    }

    @Test
    @DisplayName("Busca todos los geólogos")
    void showAllGeologists_ShouldReturnListOfGeologist() {
        List<Geologist> resultList = geologistRepository.findAll();

        List<String> lastNames = resultList.stream()
                .map(Geologist::getLastNameGeologist)
                .toList();

        assertTrue(lastNames.contains("Smith"));
        assertTrue(lastNames.contains("Jones"));
    }

    @Test
    @DisplayName("Mostrar un geólogo por id")
    void showGeologistById_ShouldReturnGeologist() {
        Geologist persist = geologistRepository.save(g1);

        Optional<Geologist> result = geologistRepository.findById(persist.getIdGeologist());
        assertTrue(result.isPresent());
        assertEquals(persist.getIdGeologist(), result.get().getIdGeologist());
        assertEquals(persist.getLastNameGeologist(), result.get().getLastNameGeologist());
    }

    @Test
    @DisplayName("No encuentra geólogo por id")
    void showGeologistById_ShouldReturnException() {
        Optional<Geologist> result = geologistRepository.findById("999L");
        assertTrue(result.isEmpty());
    }
}

