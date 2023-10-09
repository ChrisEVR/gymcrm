package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.TraineeTrainer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class TraineeTrainerDaoImp {
    @PersistenceContext
    EntityManager entityManager;

    public List<TraineeTrainer> findByTrainerId(List<Long> idList){
        String queryString = "SELECT t FROM TraineeTrainer WHERE t.id_trainer IN :idList";
        return null;
    }
}
