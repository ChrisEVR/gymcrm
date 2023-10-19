package com.epam.gymcrm.controllers;

import com.epam.gymcrm.models.TrainingType;
import com.epam.gymcrm.services.TrainingTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrainingTypeController {
    private final TrainingTypeService trainingTypeService;

    public TrainingTypeController(TrainingTypeService trainingTypeService) {
        this.trainingTypeService = trainingTypeService;
    }

    @GetMapping("/training-type")
    public ResponseEntity<List<TrainingType>> getTrainingType() {
        List<TrainingType> responseTrainingTypes = trainingTypeService.getTrainingTypes();
        return ResponseEntity.ok(responseTrainingTypes);
    }
}
