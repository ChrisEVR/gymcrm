package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.TrainingType;

import java.util.List;

public interface TrainingTypeDao {
    List<TrainingType> getTrainingTypes();

    TrainingType getTrainingTypeById(Long id);
}
