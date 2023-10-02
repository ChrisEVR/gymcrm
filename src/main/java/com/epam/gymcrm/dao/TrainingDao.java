package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.Training;

import java.util.Optional;

public interface TrainingDao {
    Optional<Training> getTraining(Long id);
    Training createTraining(Training training);
}
