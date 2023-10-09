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

@RestController
@RequestMapping("/api")
public class TraineeController {
    @Autowired
    private TraineeService traineeService;

    @PostMapping("/trainee/register")
    public ResponseEntity<Map<String, String>> registerTrainee(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "dateOfBirth", required = false) Date dateOfBirth,
            @RequestParam(value = "address", required = false) String address
    ){
        Map<String, String> response = traineeService.createTrainee(firstName, lastName, dateOfBirth, address);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/trainee/not-assigned-trainers")
    public ResponseEntity<List<Trainer>> getNotAssignedTrainers(@RequestParam(value = "username") String username){
        List<Trainer> response = traineeService.getNotAssignedTrainers(username);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/trainee/training-list")
    public ResponseEntity<List<Training>> getTrainingList(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "trainerName") String trainerName,
            @RequestParam(value = "periodFrom") Date periodFrom,
            @RequestParam(value = "periodTo") Date periodTo
    ){
        return ResponseEntity.ok(traineeService.getTrainingList(username, trainerName, periodFrom, periodTo));
    }
    @GetMapping("/trainee/{username}")
    public ResponseEntity<Trainee> getTraineeProfile(@PathVariable String username){
        return ResponseEntity.ok(traineeService.getTraineeProfile(username));
    }
    @GetMapping("/trainee/all")
    public ResponseEntity<List<Trainee>> getAllTraineeProfile(){
        return ResponseEntity.ok(traineeService.getTraineesProfile());
    }
    @DeleteMapping("/trainee/{username}")
    public ResponseEntity<String> deleteTrainee(@PathVariable String username){
        traineeService.deleteTrainee(username);
        return ResponseEntity.ok("Trainee deleted successfully.");
    }
    @PutMapping("/trainee/update")
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

    @PatchMapping("/trainee/activate-deactivate")
    public ResponseEntity<String> activateDeactivateTrainee(
            @RequestParam("username") String username,
            @RequestParam("isActive") Boolean isActive
    ) {
        traineeService.activateDeactivateTrainee(username, isActive);
        return ResponseEntity.ok("200 OK");
    }

    @PutMapping("/trainee/update-list")
    public ResponseEntity<List<Trainer>> updateTrainerList(
            @RequestParam("username") String username,
            @RequestParam("trainersList") List<String> trainersList
    ){
        return ResponseEntity.ok(
                traineeService.updateTrainerList(username, trainersList)
        );
    }
}
