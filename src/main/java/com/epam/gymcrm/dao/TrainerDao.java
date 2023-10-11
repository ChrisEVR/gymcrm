package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.Trainer;

import java.util.Optional;

public interface TrainerDao {
    Optional<Trainer> getTrainer(Long id);
    Trainer createTrainer(Trainer trainer);
    Trainer updateTrainer(Trainer newTrainer);
}
