package com.epam.gymcrm.services;

import com.epam.gymcrm.dao.TrainerRepository;
import com.epam.gymcrm.dao.TrainingRepository;
import com.epam.gymcrm.dao.TrainingTypeRepository;
import com.epam.gymcrm.dao.UserRepository;
import com.epam.gymcrm.models.Trainer;
import com.epam.gymcrm.models.Training;
import com.epam.gymcrm.models.TrainingType;
import com.epam.gymcrm.models.User;
import com.epam.gymcrm.utils.PasswordUtil;
import com.epam.gymcrm.utils.UsernameUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class TrainerService {
    private static final Logger logger = Logger.getLogger(TrainerService.class.getName());
//    private final UserRepository userRepository;
//    private final TrainerRepository trainerRepository;
//    private final TrainingRepository trainingRepository;
//    private final TrainingTypeRepository trainingTypeRepository;
    private final PasswordUtil passwordUtil;
    private final UsernameUtil usernameUtil;
    private final PasswordEncoder passwordEncoder;

    public TrainerService(
//            UserRepository userRepository,
//            TrainerRepository trainerRepository,
//            TrainingRepository trainingRepository,
//            TrainingTypeRepository trainingTypeRepository,
            PasswordUtil passwordUtil,
            UsernameUtil usernameUtil,
            PasswordEncoder passwordEncoder
    ) {
//        this.userRepository = userRepository;
//        this.trainerRepository = trainerRepository;
//        this.trainingRepository = trainingRepository;
//        this.trainingTypeRepository = trainingTypeRepository;
        this.passwordUtil = passwordUtil;
        this.usernameUtil = usernameUtil;
        this.passwordEncoder = passwordEncoder;
    }


    public Map<String, String> createTrainer(
            String firstName,
            String lastName,
            Long specialization
    ) {
        Trainer registeredTrainer;
        Trainer trainer = new Trainer();
//        TrainingType trainingType = trainingTypeRepository
//                .findById(specialization)
//                .orElseThrow(() -> new EntityNotFoundException("Training type not found with ID:" + specialization));

        Map<String, String> responseMap = new HashMap<>();

//        List<User> trainers = userRepository.findByFirstNameAndLastName(
//                firstName,
//                lastName
//        );

        String password = passwordUtil.generatePassword();

        trainer.setFirstName(firstName);
        trainer.setLastName(lastName);
//        trainer.setUsername(usernameUtil.generateUsername(trainer.getFirstName(), trainer.getLastName(), trainers));
        trainer.setPassword(passwordEncoder.encode(password));
        trainer.setActive(true);
//        trainer.setTrainingType(trainingType);

//        registeredTrainer = trainerRepository.save(trainer);

//        responseMap.put("username", registeredTrainer.getUsername());
        responseMap.put("password", password);

        return responseMap;
    }

    public Trainer getTrainerProfile(String username) {
//        Trainer trainer = trainerRepository.findByUsername(username);
//        logger.info("get trainer:" + trainer);
//        return trainer;
        return null;
    }

    public Trainer updateTrainer(
            String username,
            String firstName,
            String lastName,
            Long specialization,
            Boolean isActive
    ) {
//        Trainer trainer = trainerRepository.findByUsername(username);
//        TrainingType trainingType = trainingTypeRepository
//                .findById(specialization)
//                .orElseThrow(() -> new EntityNotFoundException("Training type not found with ID:" + specialization));

//        trainer.setFirstName(firstName);
//        trainer.setLastName(lastName);
//        trainer.setTrainingType(trainingType);
//        trainer.setActive(isActive);

//        return trainerRepository.save(trainer);
        return null;
    }

    public void activateDeactivateTrainer(String username, Boolean isActive) {
//        Trainer trainer = trainerRepository.findByUsername(username);
//        trainer.setActive(isActive);
//        trainerRepository.save(trainer);
    }

    public List<Training> getTrainingList(String username, String traineeName, Date periodFrom, Date periodTo) {
//        Trainer trainer = trainerRepository.findByUsername(username);

//        return trainingRepository.findByTrainingDateBetweenAndTrainerIdAndTrainingName(
//                trainer.getId(),
//                traineeName,
//                periodFrom,
//                periodTo
//        );
        return null;
    }
}
