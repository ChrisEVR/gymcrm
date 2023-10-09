package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.TrainingType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

@Repository
@Transactional
public class TrainingTypeDaoImp implements TrainingTypeDao {
    private static final Logger logger = Logger.getLogger(TrainingTypeDaoImp.class.getName());
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<TrainingType> getTrainingTypes() {
        return entityManager.createQuery("SELECT t FROM TrainingType t", TrainingType.class).getResultList();
    }

    @Override
    public TrainingType getTrainingTypeById(Long specialization){
        return entityManager.find(TrainingType.class, specialization);
    }

    public TrainingType findByName(String name){
        String queryString = "SELECT tt FROM TrainingType tt WHERE tt.training_type_name = :name";
        TypedQuery<TrainingType> query = entityManager.createQuery(queryString, TrainingType.class);

        query.setParameter("name", name);

        return query.getSingleResult();
    }
}
