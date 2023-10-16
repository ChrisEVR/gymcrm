package com.epam.gymcrm.services;

import com.epam.gymcrm.dao.*;
import com.epam.gymcrm.models.Trainee;
import com.epam.gymcrm.models.Trainer;
import com.epam.gymcrm.models.Training;
import com.epam.gymcrm.models.TrainingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class TrainingService {
    private static final Logger logger = Logger.getLogger(TrainingService.class.getName());

    @Autowired
    private JmsTemplate jmsTemplate;

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

        addTrainerWorkload(trainer, training);

        trainingDaoImp.createTraining(training);
    }

    private void addTrainerWorkload(
            Trainer trainer,
            Training training
    ){
        Map<String, Object> map = new HashMap<>();

        map.put("id", trainer.getId());
        map.put("username", trainer.getUsername());
        map.put("firstName", trainer.getFirstName());
        map.put("lastName", trainer.getLastName());
        map.put("isActive", trainer.getActive());
        map.put("trainingDate", training.getTrainingDate().toString());
        map.put("trainingDuration", training.getTrainingDuration());
        map.put("add", true);

        jmsTemplate.convertAndSend("reports", map);
    }
}
