package com.epam.gymcrm.services;

import com.epam.gymcrm.dao.*;
import com.epam.gymcrm.models.Trainee;
import com.epam.gymcrm.models.Trainer;
import com.epam.gymcrm.models.Training;
import com.epam.gymcrm.models.TrainingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class TrainingService {
    @Autowired
    private TrainingDaoImp trainingDaoImp;

    @Autowired
    private TraineeDaoImp traineeDaoImp;

    @Autowired
    private TrainerDaoImp trainerDaoImp;

    @Autowired
    private TrainingTypeDaoImp trainingTypeDaoImp;

    public void createTraining(
            String traineeUsername,
            String trainerUsername,
            String trainingName,
            Date trainingDate,
            Long trainingDuration,
            String trainingTypeName
    ){
        Training training = new Training();
        Trainer trainer = trainerDaoImp.findByUsername(trainerUsername);
        Trainee trainee = traineeDaoImp.findByUsername(traineeUsername);
        TrainingType trainingType = trainingTypeDaoImp.findByName(trainingTypeName);

        training.setTraining_name(trainingName);
        training.setTrainingType(trainingType);
        training.setTrainee(trainee);
        training.setTrainer(trainer);
        training.setTraining_date(trainingDate);
        training.setTraining_duration(trainingDuration);

        trainingDaoImp.createTraining(training);
    }
}
