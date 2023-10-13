package com.epam.gymcrm.services;

import com.epam.gymcrm.dao.*;
import com.epam.gymcrm.models.*;
import com.epam.gymcrm.utils.PasswordUtil;
import com.epam.gymcrm.utils.UsernameUtil;
import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.*;
import java.sql.Date;
import java.util.logging.Logger;

@CrossOrigin(origins = "*", maxAge = 3600)
@Service
public class TraineeService {
    private static final Logger logger = Logger.getLogger(TraineeService.class.getName());
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
    @Autowired
    PasswordEncoder passwordEncoder;


    public Trainee getTraineeProfile(String username){
        Trainee trainee = traineeDaoImp.findByUsername(username);
        logger.info("get trainee:" + trainee);
        return trainee;
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
        Trainee registeredTrainee;
        Trainee trainee = new Trainee();
        Map<String, String> responseMap = new HashMap<>();
        List<Trainee> trainees = traineeDaoImp.findByFirstnameAndLastname(
                firstName,
                lastName
        );
        String password = passwordUtil.generatePassword();

        trainee.setFirstName(firstName);
        trainee.setLastName(lastName);
        trainee.setUsername(usernameUtil.generateUsername(trainee.getFirstName(), trainee.getLastName(), trainees));
        trainee.setPassword(passwordEncoder.encode(password));
        trainee.setDateOfBirth(dateOfBirth);
        trainee.setAddress(address);
        trainee.setActive(true);

        registeredTrainee = traineeDaoImp.createTrainee(trainee);

        logger.info("registeredTrainee:" + registeredTrainee.toString());

        responseMap.put("username", trainee.getUsername());
        responseMap.put("password", password);

        logger.info("responseMap:" + responseMap);

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
        Trainee trainee = traineeDaoImp.findByUsername(username);
        trainee.setActive(isActive);
        traineeDaoImp.updateTrainee(trainee);
    }

    public List<Training> getTrainingList(String username, String trainerName, Date periodFrom, Date periodTo){
        Trainee trainee = traineeDaoImp.findByUsername(username);

        return trainingDaoImp.getTrainingsByUserId(
                trainee,
                trainee.getId(),
                trainerName,
                periodFrom,
                periodTo
        );
    }

    public List<Trainer> getNotAssignedTrainers(String username){
        return trainerDaoImp.getNotAssignedTrainers(username);
    }

    public List<Trainer> updateTrainerList(String username, List<String> trainersUsernameList){
        Trainee trainee = traineeDaoImp.findByUsername(username);
        List<Trainer> trainerList = trainerDaoImp.findAllByUsername(trainersUsernameList);

        for (Trainer trainer :
                trainerList) {
            trainer.removeTrainee(trainee);
        }

        trainee.setTrainers(trainerList);

        for (Trainer trainer :
                trainerList) {
            trainer.addTrainee(trainee);
        }
        logger.info("Trainee:" + trainee);

        traineeDaoImp.updateTrainee(trainee);

        return trainerList;
    }

    public Trainee loadUserByUsername(String username) throws UsernameNotFoundException {
        return traineeDaoImp.loadByUsername(username);
    }
}
