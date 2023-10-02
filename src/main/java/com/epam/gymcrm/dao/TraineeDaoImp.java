package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.Trainee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public class TraineeDaoImp implements TraineeDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Trainee> getTrainee(Long id) {
        return Optional.ofNullable(entityManager.find(Trainee.class, id));
    }

    @Override
    public Trainee createTrainee(Trainee trainee) {
        entityManager.persist(trainee);
        return trainee;
    }

    @Override
    public Optional<Trainee> updateTrainee(Trainee newTrainee) {
        return Optional.ofNullable(entityManager.merge(newTrainee));
    }

    @Override
    public void deleteTrainee(Long id){
        Trainee trainee = findById(id);
        if(trainee != null){
            entityManager.remove(trainee);
        }
    }

    public Trainee findById(Long id){
        return entityManager.find(Trainee.class, id);
    }
}
