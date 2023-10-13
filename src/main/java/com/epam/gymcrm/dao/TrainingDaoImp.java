package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.Trainee;
import com.epam.gymcrm.models.Trainer;
import com.epam.gymcrm.models.Training;
import com.epam.gymcrm.models.User;
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
import java.util.stream.Collectors;

@Repository
@Transactional
public class TrainingDaoImp implements TrainingDao {
    private static final Logger logger = Logger.getLogger(TrainingDaoImp.class.getName());

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

    public List<Training> getTrainingsByUserId(
            User user,
            Long id,
            String name,
            Date periodFrom,
            Date periodTo
    ) {
        String whereClause = user instanceof Trainer ? "WHERE t.trainer.id = :idUser " :
                "WHERE t.trainee.id = :idUser ";
        String concatenatedName = user instanceof Trainer ? "t.trainee.firstName, ' ', t.trainee.lastName" :
                "t.trainer.firstName, ' ', t.trainer.lastName";

        TypedQuery<Training> query = entityManager.createQuery(
                "SELECT t " +
                    "FROM Training t " +
                    whereClause +
                    "AND (:name is null OR CONCAT(" + concatenatedName + ") " +
                    "LIKE :name) " +
                    "AND (:periodFrom is null OR t.trainingDate >= :periodFrom) " +
                    "AND (:periodTo is null OR t.trainingDate <= :periodTo)",
                Training.class);

        query
                .setParameter("idUser", id)
                .setParameter("name", name == null ? null : "%" + name + "%")
                .setParameter("periodFrom", periodFrom)
                .setParameter("periodTo", periodTo);

        logger.info("idTrainer:" + id +
                " name:" + name +
                " periodFrom:" + periodFrom +
                " periodTo:" + periodTo
        );

        return query.getResultList();
    }
}
