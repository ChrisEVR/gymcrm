package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.Training;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TrainingDaoImp implements TrainingDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<Training> getTraining(Long id) {
        return Optional.ofNullable(entityManager.find(Training.class, id));
    }

    @Override
    public Training createTraining(Training training) {
        entityManager.persist(training);
        return training;
    }
}
