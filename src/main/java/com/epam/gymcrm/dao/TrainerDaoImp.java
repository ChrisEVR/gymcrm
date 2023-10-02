package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.Trainer;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public class TrainerDaoImp implements TrainerDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Optional<Trainer> getTrainer(Long id) {
        return Optional.ofNullable(entityManager.find(Trainer.class, id));
    }

    @Override
    public Trainer createTrainer(Trainer trainer) {
        entityManager.persist(trainer);
        return trainer;
    }

    @Override
    public Trainer updateTrainer(Trainer newTrainer) {
        return entityManager.merge(newTrainer);
    }
}
