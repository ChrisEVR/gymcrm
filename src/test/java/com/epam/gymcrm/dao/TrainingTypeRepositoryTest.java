package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.TrainingType;
import io.cucumber.java.bs.A;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class TrainingTypeRepositoryTest {
//    private final TrainingTypeRepository trainingTypeRepository;

    private static final Logger logger = Logger.getLogger(TrainingTypeRepositoryTest.class.getName());

    @Autowired
    public TrainingTypeRepositoryTest(
//            TrainingTypeRepository trainingTypeRepository
    ) {
//        this.trainingTypeRepository = trainingTypeRepository;
    }

    @Test
    public void should_get_all_training_types(){
        List<String> trainingTypes = Arrays.asList("Fitness", "Yoga", "Zumba", "Stretching", "Resistance");
//        List<TrainingType> trainingTypeList = trainingTypeRepository.findAll();

//        for(int i = 0; i < trainingTypeList.size(); ++i){
//            TrainingType trainingType = trainingTypeList.get(i);
//            Assertions.assertTrue(i + 1 == trainingType.getId() &&
//                    Objects.equals(trainingType.getTrainingTypeName(), trainingTypes.get(i)));
//        }
    }

    @Test
    public void should_get_resistance_training_type_by_id(){
//        TrainingType trainingType = trainingTypeRepository.findById(5L)
//                .orElseThrow(() -> new EntityNotFoundException("Entity not found with given ID."));
//        Assertions.assertEquals(trainingType.getTrainingTypeName(), "Resistance");
    }

    @Test
    public void should_get_fitness_training_type_by_name(){
//        TrainingType trainingType = trainingTypeRepository.findByTrainingTypeName("Resistance");
//        Assertions.assertEquals(trainingType.getId(), 5L);
    }
}
