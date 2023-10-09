package com.epam.gymcrm.services;

import com.epam.gymcrm.dao.*;
import com.epam.gymcrm.models.Trainee;
import com.epam.gymcrm.models.Trainer;
import com.epam.gymcrm.models.Training;
import com.epam.gymcrm.models.TrainingType;
import com.epam.gymcrm.utils.PasswordUtil;
import com.epam.gymcrm.utils.UsernameUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TrainerService {
    @Autowired
    TrainerDaoImp trainerDaoImp;
    @Autowired
    TraineeDaoImp traineeDaoImp;
    @Autowired
    TrainingDaoImp trainingDaoImp;
    @Autowired
    TrainingTypeDaoImp trainingTypeDaoImp;
    @Autowired
    PasswordUtil passwordUtil;
    @Autowired
    UsernameUtil usernameUtil;

    public Map<String, String> createTrainer(
            String firstName,
            String lastName,
            Long specialization
    ){
        Trainer registeredTrainer;
        Trainer trainer = new Trainer();
        TrainingType trainingType = trainingTypeDaoImp.getTrainingTypeById(specialization);
        Map<String, String> responseMap = new HashMap<>();

        List<Trainer> trainers = trainerDaoImp.findByFirstnameAndLastname(
                firstName,
                lastName
        );

        trainer.setFirstName(firstName);
        trainer.setLastName(lastName);
        trainer.setUsername(usernameUtil.generateUsername(trainer.getFirstName(), trainer.getLastName(), trainers));
        trainer.setPassword(passwordUtil.generatePassword());
        trainer.setActive(true);
        trainer.setTrainingType(trainingType);

        registeredTrainer = trainerDaoImp.createTrainer(trainer, specialization);

        responseMap.put("username", registeredTrainer.getUsername());
        responseMap.put("password", registeredTrainer.getPassword());

        return responseMap;
    }

    public Trainer updateTrainer(
            String username,
            String firstName,
            String lastName,
            Long specialization,
            Boolean isActive
    ){
        Trainer trainer = trainerDaoImp.findByUsername(username);
        TrainingType trainingType = trainingTypeDaoImp.getTrainingTypeById(specialization);

        trainer.setFirstName(firstName);
        trainer.setLastName(lastName);
        trainer.setTrainingType(trainingType);
        trainer.setActive(isActive);

        return trainerDaoImp.updateTrainer(trainer);
    }

    public void activateDeactivateTrainer(String username, Boolean isActive){
        Integer updatedRows = trainerDaoImp.activateDeactivateTrainer(username, isActive);

        if(updatedRows == 0){
            throw new EntityNotFoundException("Trainer not found with given username");
        }
    }

    public List<Training> getTrainingList(String username, String traineeName, Date periodFrom, Date periodTo){
        Trainer trainer = trainerDaoImp.findByUsername(username);
        List<Long> traineesId = traineeDaoImp.findAllTraineesId();
        List<Trainee> traineeList = traineeDaoImp.findByIdsAndName(traineesId, traineeName);
        List<Long> idList = traineeList.stream().map(Trainee::getId).toList();

        return trainingDaoImp.getTrainingsByTrainerId(
                trainer.getId(),
                idList,
                periodFrom.toString(),
                periodTo.toString()
        );
    }
}
