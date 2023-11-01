package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.Trainer;
import com.epam.gymcrm.models.TrainingType;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class TrainerRepositoryTest {
    private final TrainerRepository trainerRepository;
    private final TrainingTypeRepository trainingTypeRepository;

    @Autowired
    public TrainerRepositoryTest(TrainerRepository trainerRepository, TrainingTypeRepository trainingTypeRepository) {
        this.trainerRepository = trainerRepository;
        this.trainingTypeRepository = trainingTypeRepository;
    }
//
//    @Order(1)
//    @Test
//    public void should_save_trainer(){
//        Trainer trainer = new Trainer();
//        TrainingType trainingType = trainingTypeRepository.findById(1L)
//                .orElseThrow();
//
//        trainer.setUsername("test.trainer");
//        trainer.setFirstName("test");
//        trainer.setLastName("training");
//        trainer.setPassword("password");
//        trainer.setActive(true);
//        trainer.setTrainingType(trainingType);
//
//        Trainer createdTrainer = trainerRepository.save(trainer);
//
//        Assertions.assertEquals(createdTrainer, trainer);
//    }
//
//    @Order(2)
//    @Test
//    public void should_update_trainer(){
//        Trainer trainer = trainerRepository.findByUsername("test.trainer");
//        TrainingType trainingType = trainingTypeRepository.findById(2L)
//                .orElseThrow(() -> new EntityNotFoundException("Entity not found with given ID."));;
//
//        trainer.setUsername("test.trainer");
//        trainer.setFirstName("update");
//        trainer.setLastName("training");
//        trainer.setPassword("update.password");
//        trainer.setActive(true);
//        trainer.setTrainingType(trainingType);
//
//        Trainer updatedTrainer = trainerRepository.save(trainer);
//
//        Assertions.assertEquals(updatedTrainer, trainer);
//    }
//
//    @Order(2)
//    @Test
//    public void should_find_trainer(){
//        Trainer trainer = new Trainer();
//        TrainingType trainingType = trainingTypeRepository.findById(2L)
//                .orElseThrow(() -> new EntityNotFoundException("Entity not found with given ID."));
//
//        trainer.setUsername("test.trainer");
//        trainer.setFirstName("update");
//        trainer.setLastName("training");
//        trainer.setPassword("update.password");
//        trainer.setActive(true);
//        trainer.setTrainingType(trainingType);
//
//        Trainer foundTrainer = trainerRepository.findByUsername("test.trainer");
//
//        Assertions.assertEquals(trainer, foundTrainer);
//    }
//
//    @Order(3)
//    @Test
//    public void should_get_all_trainers(){
//        List<String> usernames = new LinkedList<>(List.of("test.trainer"));
//        List<Trainer> trainerList = trainerRepository.findByUsernamesInList(usernames);
//        Trainer trainer = new Trainer();
//        TrainingType trainingType = trainingTypeRepository.findById(2L)
//                .orElseThrow(() -> new EntityNotFoundException("Entity not found with given ID."));
//
//        trainer.setUsername("test.trainer");
//        trainer.setFirstName("update");
//        trainer.setLastName("training");
//        trainer.setPassword("update.password");
//        trainer.setActive(true);
//        trainer.setTrainingType(trainingType);
//
//        Assertions.assertEquals(1, trainerList.size());
//        Assertions.assertEquals(trainer, trainerList.get(0));
//    }
//
//    @Order(4)
//    @Test
//    public void should_get_not_assigned_trainers(){
//        List<Trainer> trainerList = trainerRepository.findNotAssignedTrainers("test.trainee");
//        Trainer trainer = new Trainer();
//        TrainingType trainingType = trainingTypeRepository.findById(2L)
//                .orElseThrow(() -> new EntityNotFoundException("Entity not found with given ID."));;
//
//        trainer.setUsername("test.trainer");
//        trainer.setFirstName("update");
//        trainer.setLastName("training");
//        trainer.setPassword("update.password");
//        trainer.setActive(true);
//        trainer.setTrainingType(trainingType);
//
//        Assertions.assertEquals(1, trainerList.size());
//        Assertions.assertEquals(trainer, trainerList.get(0));
//    }
}
