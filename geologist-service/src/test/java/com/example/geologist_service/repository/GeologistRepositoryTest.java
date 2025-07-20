package com.example.geologist_service.repository;

import com.example.geologist_service.enums.Gender;
import com.example.geologist_service.exeption.ResourceNotFoundException;
import com.example.geologist_service.models.Geologist;
import com.example.geologist_service.service.GeologistService;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GeologistRepositoryTest {
    @Autowired
    private GeologistRepository geologistRepository;

    @Autowired
    private TestEntityManager entityManager;

    @MockBean
    private GeologistService geologistService;


    @AfterEach
    void cleanUp() {
        geologistRepository.deleteAll();
    }

    @Test
    @DisplayName("Prueba que crea un geólogo en el repositorio")
    void createGeologist_ShouldReturnListOfGeologist(){
        //Arrange
        Geologist g1 = new Geologist();
        g1.setLastNameGeologist("Smith");
        g1.setNameGeologist("John");
        g1.setSpecialization("Mineralogy");
        g1.setEmailGeologist("john.smith@example.com");
        g1.setYearsOfExperience(5.0);
        g1.setGender(Gender.MALE);

        Geologist g2 = new Geologist();
        g2.setLastNameGeologist("Jones");

        g2.setNameGeologist("Alice");
        g2.setSpecialization("Petrology");
        g2.setEmailGeologist("alice.jones@example.com");
        g2.setYearsOfExperience(7.0);
        g2.setGender(Gender.FEMALE);

        List<Geologist> list = List.of(g1, g2);

        //Act
        List<Geologist> savedList= geologistRepository.saveAll(list);

        //Assert
        Assertions.assertEquals(2, savedList.size());
        Assertions.assertEquals("Smith", savedList.get(0).getLastNameGeologist());
        Assertions.assertEquals("Jones", savedList.get(1).getLastNameGeologist());
    }

    @Test
    @DisplayName("Prueba busca todos los geólogos en el repositorio")
    void showAllGeologists_ShouldReturnListOfGeologist(){
        //Arrange
        Geologist g1 = new Geologist();
        g1.setNameGeologist("Alice");
        g1.setLastNameGeologist("Smith");
        g1.setSpecialization("Paleontology");
        g1.setEmailGeologist("alice.smith@example.com");
        g1.setYearsOfExperience(5.0);
        g1.setGender(Gender.FEMALE);

        Geologist g2 = new Geologist();
        g2.setNameGeologist("Bob");
        g2.setLastNameGeologist("Jones");
        g2.setSpecialization("Mineralogy");
        g2.setEmailGeologist("bob.jones@example.com");
        g2.setYearsOfExperience(8.0);
        g2.setGender(Gender.MALE);

        geologistRepository.saveAll(List.of(g1, g2));

        //Act
        List<Geologist> resultList= geologistRepository.findAll();

        //Asser
        Assertions.assertEquals(2, resultList.size());
        Assertions.assertEquals("Smith", resultList.get(0).getLastNameGeologist());
        Assertions.assertEquals("Jones", resultList.get(1).getLastNameGeologist());

    }

    @Test
    @DisplayName("Teste que prueba mostrar un geólogo por id")
    void showGeologistById_ShouldReturnGeologist(){
        //Arrange
        Geologist g1 = new Geologist();
        g1.setNameGeologist("Alice");
        g1.setLastNameGeologist("Smith");
        g1.setEmailGeologist("alice.smith@example.com");
        g1.setSpecialization("Sedimentology");
        g1.setYearsOfExperience(6.0);
        g1.setGender(Gender.FEMALE); // si aplica

        //Persist
        Geologist persist = entityManager.persist(g1);
        entityManager.flush(); // fuerza sincronización con la base de datos

        //Act
        Optional<Geologist> result= geologistRepository.findById(g1.getIdGeologist());

        //Assert
        assertTrue(result.isPresent());
        Assertions.assertEquals(persist.getIdGeologist(), result.get().getIdGeologist());
        Assertions.assertEquals(persist.getLastNameGeologist(), result.get().getLastNameGeologist());
    }

    @Test
    @DisplayName("Teste que prueba mostrar un geólogo por id si NO lo encuentra")
    void showGeologistById_ShouldReturnException(){
        // Arrange
        Long invalidId = 999L;

        // Act
        Optional<Geologist> result = geologistRepository.findById(invalidId);

        // Assert
        assertTrue(result.isEmpty());
    }


}
