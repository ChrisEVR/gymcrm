package com.epam.gymcrm.services;

import com.epam.gymcrm.dao.*;
import com.epam.gymcrm.models.Trainee;
import com.epam.gymcrm.models.Trainer;
import com.epam.gymcrm.models.Training;
import com.epam.gymcrm.models.TrainingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class TrainingService {
    private static final Logger logger = Logger.getLogger(TrainingService.class.getName());
    private final TrainingRepository trainingRepository;
    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;
    private final TrainingTypeRepository trainingTypeRepository;
    private final ReportService reportService;

    public TrainingService(
            TrainingRepository trainingRepository,
            TraineeRepository traineeRepository,
            TrainerRepository trainerRepository,
            TrainingTypeRepository trainingTypeRepository,
            ReportService reportService
    ) {
        this.trainingRepository = trainingRepository;
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
        this.trainingTypeRepository = trainingTypeRepository;
        this.reportService = reportService;
    }

    public void createTraining(
            String traineeUsername,
            String trainerUsername,
            String trainingName,
            Date trainingDate,
            Long trainingDuration,
            String trainingTypeName
    ) {
        Training training = new Training();
        Trainer trainer = trainerRepository.findByUsername(trainerUsername);
        Trainee trainee = traineeRepository.findByUsername(traineeUsername);
        TrainingType trainingType = trainingTypeRepository.findByTrainingTypeName(trainingTypeName);

        training.setTrainingName(trainingName);
        training.setTrainingType(trainingType);
        training.setTrainee(trainee);
        training.setTrainer(trainer);
        training.setTrainingDate(trainingDate);
        training.setTrainingDuration(trainingDuration);
        trainee.addTraining(training);
        trainer.addTraining(training);

        logger.info("training:---" + training.toString());

        reportService.addTrainerWorkload(trainer, training);

        trainingRepository.save(training);
    }

}
