package com.epam.gymcrm.controllers;

import com.epam.gymcrm.models.Trainer;
import com.epam.gymcrm.models.Training;
import com.epam.gymcrm.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TrainerController {
    @Autowired
    private TrainerService trainerService;

    @PostMapping("/trainer/register")
    public ResponseEntity<Map<String, String>> registerTrainer(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "specialization") Long specialization
    ){
        Map<String, String> response = trainerService.createTrainer(firstName, lastName, specialization);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/trainer/training-list")
    public ResponseEntity<List<Training>> getTrainingList(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "traineeName", required = false) String traineeName,
            @RequestParam(value = "periodFrom", required = false) Date periodFrom,
            @RequestParam(value = "periodTo", required = false) Date periodTo
    ){
        return ResponseEntity.ok(trainerService.getTrainingList(username, traineeName, periodFrom, periodTo));
    }
    @PatchMapping("/trainer/activate-deactivate")
    public ResponseEntity<String> activateDeactivateTrainer(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "isActive") Boolean isActive
    ){
        trainerService.activateDeactivateTrainer(username, isActive);

        return ResponseEntity.ok("200 OK");
    }

    @PutMapping("/trainer/update")
    public ResponseEntity<Trainer> updateTrainer(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "specialization") Long specialization,
            @RequestParam(value = "isActive") Boolean isActive
    ){
        return ResponseEntity.ok(
                trainerService.updateTrainer(username, firstName, lastName, specialization, isActive)
        );
    }
}
