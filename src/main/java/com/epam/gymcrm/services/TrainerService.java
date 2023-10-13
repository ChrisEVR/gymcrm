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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class TrainerService {
    private static final Logger logger = Logger.getLogger(TrainerService.class.getName());
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

    @Autowired
    PasswordEncoder passwordEncoder;

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

        String password = passwordUtil.generatePassword();

        trainer.setFirstName(firstName);
        trainer.setLastName(lastName);
        trainer.setUsername(usernameUtil.generateUsername(trainer.getFirstName(), trainer.getLastName(), trainers));
        trainer.setPassword(passwordEncoder.encode(password));
        trainer.setActive(true);
        trainer.setTrainingType(trainingType);

        registeredTrainer = trainerDaoImp.createTrainer(trainer);

        responseMap.put("username", registeredTrainer.getUsername());
        responseMap.put("password", password);

        return responseMap;
    }

    public Trainer getTrainerProfile(String username){
        Trainer trainer = trainerDaoImp.findByUsername(username);
        logger.info("get trainer:" + trainer);
        return trainer;
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
        Trainer trainer = trainerDaoImp.findByUsername(username);
        trainer.setActive(isActive);
        trainerDaoImp.updateTrainer(trainer);
    }

    public List<Training> getTrainingList(String username, String traineeName, Date periodFrom, Date periodTo){
        Trainer trainer = trainerDaoImp.findByUsername(username);

        return trainingDaoImp.getTrainingsByUserId(
                trainer,
                trainer.getId(),
                traineeName,
                periodFrom,
                periodTo
        );
    }
}
