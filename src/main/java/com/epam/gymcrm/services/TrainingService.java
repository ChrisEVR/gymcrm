package com.epam.gymcrm.services;

import com.epam.gymcrm.dao.*;
import com.epam.gymcrm.models.Trainee;
import com.epam.gymcrm.models.Trainer;
import com.epam.gymcrm.models.Training;
import com.epam.gymcrm.models.TrainingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.logging.Logger;

@Service
public class TrainingService {
    private static final Logger logger = Logger.getLogger(TrainingService.class.getName());

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

        training.setTrainingName(trainingName);
        training.setTrainingType(trainingType);
        training.setTrainee(trainee);
        training.setTrainer(trainer);
        training.setTrainingDate(trainingDate);
        training.setTrainingDuration(trainingDuration);

        trainee.addTraining(training);
        trainer.addTraining(training);

        logger.info("training:---" + training.toString());

        trainingDaoImp.createTraining(training);
    }
}
