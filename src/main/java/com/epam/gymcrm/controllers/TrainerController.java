package com.epam.gymcrm.controllers;

import com.epam.gymcrm.models.Trainer;
import com.epam.gymcrm.models.Training;
import com.epam.gymcrm.services.TrainerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {
    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {

        this.trainerService = trainerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerTrainer(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "specialization") Long specialization
    ) {
        Map<String, String> response = trainerService.createTrainer(firstName, lastName, specialization);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/training-list")
    public ResponseEntity<List<Training>> getTrainingList(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "traineeName", required = false) String traineeName,
            @RequestParam(value = "periodFrom", required = false) Date periodFrom,
            @RequestParam(value = "periodTo", required = false) Date periodTo
    ) {
        return ResponseEntity.ok(trainerService.getTrainingList(username, traineeName, periodFrom, periodTo));
    }

    @PatchMapping("/activate-deactivate")
    public ResponseEntity<String> activateDeactivateTrainer(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "isActive") Boolean isActive
    ) {
        trainerService.activateDeactivateTrainer(username, isActive);

        return ResponseEntity.ok("Status updated successfully!");
    }

    @PutMapping("/update")
    public ResponseEntity<Trainer> updateTrainer(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "specialization") Long specialization,
            @RequestParam(value = "isActive") Boolean isActive
    ) {
        return ResponseEntity.ok(
                trainerService.updateTrainer(username, firstName, lastName, specialization, isActive)
        );
    }

    @GetMapping("/{username}")
    public ResponseEntity<Trainer> getTrainerProfile(@PathVariable String username) {
        return ResponseEntity.ok(trainerService.getTrainerProfile(username));
    }
}
