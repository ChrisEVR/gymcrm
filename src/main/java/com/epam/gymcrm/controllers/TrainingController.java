package com.epam.gymcrm.controllers;

import com.epam.gymcrm.services.TrainingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/api/training")
public class TrainingController {
    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTraining(
            @RequestParam("traineeUsername") String traineeUsername,
            @RequestParam("trainerUsername") String trainerUsername,
            @RequestParam("trainingName") String trainingName,
            @RequestParam("trainingDate") Date trainingDate,
            @RequestParam("trainingDuration") Long trainingDuration,
            @RequestParam("trainingType") String trainingTypeName
    ) throws JsonProcessingException {
        trainingService.createTraining(
                traineeUsername,
                trainerUsername,
                trainingName,
                trainingDate,
                trainingDuration,
                trainingTypeName
        );

        //orElseThrow(() -> new ResourceNotFoundException(username + " NOT found"));
        return ResponseEntity.ok("Training added successfully!");
    }
}
