package com.epam.gymcrm.controllers;

import com.epam.gymcrm.models.Trainee;
import com.epam.gymcrm.models.Trainer;
import com.epam.gymcrm.models.Training;
import com.epam.gymcrm.services.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/trainee")
public class TraineeController {
    private static final Logger logger = Logger.getLogger(TraineeController.class.getName());
    @Autowired
    private TraineeService traineeService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerTrainee(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "dateOfBirth", required = false) Date dateOfBirth,
            @RequestParam(value = "address", required = false) String address
    ){
        Map<String, String> response = traineeService.createTrainee(firstName, lastName, dateOfBirth, address);
        logger.info("response:" + response.toString());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/not-assigned-trainers")
    public ResponseEntity<List<Trainer>> getNotAssignedTrainers(@RequestParam(value = "username") String username){
        List<Trainer> response = traineeService.getNotAssignedTrainers(username);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/training-list")
    public ResponseEntity<List<Training>> getTrainingList(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "trainerName") String trainerName,
            @RequestParam(value = "periodFrom") Date periodFrom,
            @RequestParam(value = "periodTo") Date periodTo
    ){
        return ResponseEntity.ok(traineeService.getTrainingList(username, trainerName, periodFrom, periodTo));
    }
    @GetMapping("/{username}")
    public ResponseEntity<Trainee> getTraineeProfile(@PathVariable String username){
        return ResponseEntity.ok(traineeService.getTraineeProfile(username));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Trainee>> getAllTraineeProfile(){
        return ResponseEntity.ok(traineeService.getTraineesProfile());
    }
    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteTrainee(@PathVariable String username){
        traineeService.deleteTrainee(username);
        return ResponseEntity.ok("Trainee deleted successfully.");
    }
    @PutMapping("/update")
    public ResponseEntity<Trainee> updateTrainee(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "dateOfBirth", required = false) Date dateOfBirth,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "isActive") Boolean isActive
    ){
        return ResponseEntity.ok(
                traineeService.updateTrainee(username, firstName, lastName, dateOfBirth, address, isActive)
        );
    }

    @PatchMapping("/activate-deactivate")
    public ResponseEntity<String> activateDeactivateTrainee(
            @RequestParam("username") String username,
            @RequestParam("isActive") Boolean isActive
    ) {
        traineeService.activateDeactivateTrainee(username, isActive);
        return ResponseEntity.ok("200 OK");
    }

    @PutMapping("/update-list")
    public ResponseEntity<List<Trainer>> updateTrainerList(
            @RequestParam("username") String username,
            @RequestParam("trainersList") List<String> trainersList
    ){
        return ResponseEntity.ok(
                traineeService.updateTrainerList(username, trainersList)
        );
    }
}
