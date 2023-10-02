package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.Trainee;
import java.util.Optional;

public interface TraineeDao {
    Optional<Trainee> getTrainee(Long id);
    Trainee createTrainee(Trainee trainee);
    Optional<Trainee> updateTrainee(Trainee newTrainee);
    void deleteTrainee(Long id);
}
