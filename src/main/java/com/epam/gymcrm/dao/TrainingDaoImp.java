package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.Trainee;
import com.epam.gymcrm.models.Trainer;
import com.epam.gymcrm.models.Training;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@Repository
@Transactional
public class TrainingDaoImp implements TrainingDao {
    private static final Logger logger = Logger.getLogger(TrainingTypeDaoImp.class.getName());

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

    //TODO: Reduce following two functions
    public List<Training> getTrainingsByTrainerId(
            Long idTrainer,
            List<Long> listIdTrainee,
            String periodFrom,
            String periodTo
    ) {
        StringBuilder queryString = new StringBuilder("SELECT t FROM Training t");
        Map<String, Object> params = new HashMap<>();
        TypedQuery<Training> query;

        if(idTrainer != null || listIdTrainee != null || periodFrom != null || periodTo != null ){
            queryString.append(" WHERE");
        }

        if(idTrainer != null){
            queryString.append(" AND t.trainer_id = :idTrainer");
            params.put("idTrainer", idTrainer);
        }

        if(listIdTrainee != null){
            queryString.append(" AND t.trainee_id IN :listIdTrainee");
            params.put("listIdTrainee", listIdTrainee);
        }

        if(periodFrom != null){
            queryString.append(" AND :periodFrom <= t.training_date");
            params.put("periodFrom", periodFrom);
        }

        if(periodTo != null){
            queryString.append(" AND :periodTo >= t.training_date");
            params.put("periodTo", periodTo);
        }

        query = entityManager.createQuery(queryString.toString(), Training.class);

        for(Map.Entry<String, Object> entry : params.entrySet()){
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.getResultList();
    }

    public List<Training> getTrainingsByTraineeId(
            Long listIdTrainer,
            List<Long> listIdTrainee,
            String periodFrom,
            String periodTo
    ) {
        StringBuilder queryString = new StringBuilder("SELECT t FROM Training t");
        Map<String, Object> params = new HashMap<>();
        TypedQuery<Training> query;

        if(listIdTrainer != null || listIdTrainee != null || periodFrom != null || periodTo != null ){
            queryString.append(" WHERE");
        }

        if(listIdTrainer != null){
            queryString.append(" AND t.trainer_id = :idTrainer");
            params.put("idTrainer", listIdTrainer);
        }

        if(listIdTrainee != null){
            queryString.append(" AND t.trainee_id IN :listIdTrainee");
            params.put("listIdTrainee", listIdTrainee);
        }

        if(periodFrom != null){
            queryString.append(" AND :periodFrom <= t.training_date");
            params.put("periodFrom", periodFrom);
        }

        if(periodTo != null){
            queryString.append(" AND :periodTo >= t.training_date");
            params.put("periodTo", periodTo);
        }

        query = entityManager.createQuery(queryString.toString(), Training.class);

        for(Map.Entry<String, Object> entry : params.entrySet()){
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.getResultList();
    }
}
