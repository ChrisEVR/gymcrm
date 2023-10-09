package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.Trainee;
import com.epam.gymcrm.models.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TraineeDao {
    Optional<Trainee> findById(Long id);
    Trainee createTrainee(Trainee trainee);
    Trainee updateTrainee(Trainee newTrainee);
    Integer deleteTraineeByUsername(String username);
}