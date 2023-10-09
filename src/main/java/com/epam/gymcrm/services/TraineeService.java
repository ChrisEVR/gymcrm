package com.epam.gymcrm.services;

import com.epam.gymcrm.dao.*;
import com.epam.gymcrm.models.*;
import com.epam.gymcrm.utils.PasswordUtil;
import com.epam.gymcrm.utils.UsernameUtil;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.sql.Date;

@Service
public class TraineeService {
    @Autowired
    private TraineeTrainerDaoImp traineeTrainerDaoImp;
    @Autowired
    private TraineeDaoImp traineeDaoImp;
    @Autowired
    private TrainingDaoImp trainingDaoImp;
    @Autowired
    private TrainerDaoImp trainerDaoImp;
    @Autowired
    private PasswordUtil passwordUtil;
    @Autowired
    private UsernameUtil usernameUtil;

    public Trainee getTraineeProfile(String username){
        return traineeDaoImp.findByUsername(username);
    }

    public List<Trainee> getTraineesProfile(){
        return traineeDaoImp.findAllTrainees();
    }

    public Map<String, String> createTrainee(
            String firstName,
            String lastName,
            Date dateOfBirth,
            String address
    ){
        Trainee registeredTrainee, trainee = new Trainee();
        Map<String, String> responseMap = new HashMap<>();
        List<Trainee> trainees = traineeDaoImp.findByFirstnameAndLastname(
                firstName,
                lastName
        );

        trainee.setFirstName(firstName);
        trainee.setLastName(lastName);
        trainee.setUsername(usernameUtil.generateUsername(trainee.getFirstName(), trainee.getLastName(), trainees));
        trainee.setPassword(passwordUtil.generatePassword());
        trainee.setDateOfBirth(dateOfBirth);
        trainee.setAddress(address);
        trainee.setActive(true);

        registeredTrainee = traineeDaoImp.createTrainee(trainee);

        responseMap.put("username", registeredTrainee.getUsername());
        responseMap.put("password", registeredTrainee.getPassword());

        return responseMap;
    }

    public Trainee updateTrainee(
            String username,
            String firstName,
            String lastName,
            Date dateOfBirth,
            String address,
            Boolean isActive
    ){
        Trainee trainee = traineeDaoImp.findByUsername(username);

        trainee.setFirstName(firstName);
        trainee.setLastName(lastName);
        trainee.setDateOfBirth(dateOfBirth);
        trainee.setAddress(address);
        trainee.setActive(isActive);

        return traineeDaoImp.updateTrainee(trainee);
    }

    public void deleteTrainee(String username){
        Integer deletedRows = traineeDaoImp.deleteTraineeByUsername(username);

        if(deletedRows == 0){
            throw new EntityNotFoundException("Trainee not found with given username");
        }
    }

    public void activateDeactivateTrainee(String username, Boolean isActive){
        Integer updatedRows = traineeDaoImp.activateDeactivateTrainee(username, isActive);

        if(updatedRows == 0){
            throw new EntityNotFoundException("Trainee not found with given username");
        }
    }

    public List<Training> getTrainingList(String username, String trainerName, Date periodFrom, Date periodTo){
        Trainee trainee = traineeDaoImp.findByUsername(username);
        List<Long> trainersId = trainerDaoImp.findAllTrainersId();
        List<Trainer> trainerList = trainerDaoImp.findByIdsAndName(trainersId, trainerName);
        List<Long> idList = trainerList.stream().map(Trainer::getId).toList();

        return trainingDaoImp.getTrainingsByTraineeId(
                trainee.getId(),
                idList,
                periodFrom.toString(),
                periodTo.toString()
        );
    }

    public List<Trainer> getNotAssignedTrainers(String username){
        return trainerDaoImp.getNotAssignedTrainers(username);
    }

    public List<Trainer> updateTrainerList(String username, List<String> trainersUsernameList){
        List<Trainer> trainerList = trainerDaoImp.findAllByUsername(trainersUsernameList);
        List<Long> idList = trainerList.stream().map(Trainer::getId).toList();
        List<TraineeTrainer> traineeTrainerList = traineeTrainerDaoImp.findByTrainerId(idList);
        Trainee trainee = traineeDaoImp.findByUsername(username);

        traineeDaoImp.updateTrainerList(trainee, traineeTrainerList);

        return trainerList;
    }
}
