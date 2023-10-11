package com.epam.gymcrm.controllers;

import com.epam.gymcrm.models.Training;
import com.epam.gymcrm.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import org.springframework.web.servlet.function.ServerResponse;

import java.sql.Date;

@RestController
@RequestMapping("/api/training")
public class TrainingController {
    @Autowired
    private TrainingService trainingService;

    @PostMapping("/register")
    public ResponseEntity.BodyBuilder registerTraining(
            @RequestParam("traineeUsername") String traineeUsername,
            @RequestParam("trainerUsername") String trainerUsername,
            @RequestParam("trainingName") String trainingName,
            @RequestParam("trainingDate") Date trainingDate,
            @RequestParam("trainingDuration") Long trainingDuration,
            @RequestParam("trainingType") String trainingTypeName
    ){
        trainingService.createTraining(
                traineeUsername,
                trainerUsername,
                trainingName,
                trainingDate,
                trainingDuration,
                trainingTypeName
        );

        //orElseThrow(() -> new ResourceNotFoundException(username + " NOT found"));
        return ResponseEntity.ok();
    }
}
