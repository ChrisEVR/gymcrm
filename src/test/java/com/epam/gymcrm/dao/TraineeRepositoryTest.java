package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.Trainee;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.logging.Logger;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class TraineeRepositoryTest {
    private final TraineeRepository traineeRepository;

    private static final Logger logger = Logger.getLogger(UserRepositoryTest.class.getName());

    @Autowired
    public TraineeRepositoryTest(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    @Order(1)
    @Test
    public void should_save_trainee(){
        Trainee trainee = new Trainee();

        trainee.setUsername("test.trainee");
        trainee.setFirstName("test");
        trainee.setLastName("trainee");
        trainee.setPassword("password");
        trainee.setAddress("Gante 581");
        trainee.setActive(true);

        Trainee createdTrainee = traineeRepository.save(trainee);

        Assertions.assertEquals(trainee, createdTrainee);
    }

    @Order(2)
    @Test
    public void should_update_trainee(){
        Trainee trainee = traineeRepository.findByUsername("test.trainee");

        trainee.setUsername("test.trainee");
        trainee.setFirstName("update");
        trainee.setLastName("trainee");
        trainee.setPassword("password");
        trainee.setAddress("Gante 581 - 7");
        trainee.setActive(true);

        Trainee updatedTrainee = traineeRepository.save(trainee);

        Assertions.assertEquals(trainee, updatedTrainee);
    }

    @Order(3)
    @Test
    public void should_find_trainee(){
        Trainee trainee = new Trainee();

        trainee.setUsername("test.trainee");
        trainee.setFirstName("update");
        trainee.setLastName("trainee");
        trainee.setPassword("password");
        trainee.setAddress("Gante 581 - 7");
        trainee.setActive(true);

        Trainee foundTrainee = traineeRepository.findByUsername("test.trainee");

        Assertions.assertEquals(trainee, foundTrainee);
    }

    @Order(4)
    @Test
    public void should_return_all_trainees(){
        Trainee trainee = new Trainee();

        trainee.setUsername("test.trainee");
        trainee.setFirstName("update");
        trainee.setLastName("trainee");
        trainee.setPassword("password");
        trainee.setAddress("Gante 581 - 7");
        trainee.setActive(true);

        List<Trainee> traineeList = traineeRepository.findAll();

        logger.info("traineeList:" + traineeList);

        Assertions.assertEquals(1, traineeList.size());
        Assertions.assertEquals(trainee, traineeList.get(0));
    }

    @Order(5)
    @Test
    public void should_delete_trainee(){
        traineeRepository.deleteByUsername("test.trainee");
        Trainee trainee = traineeRepository.findByUsername("test.trainee");
        Assertions.assertNull(trainee);
    }

}
