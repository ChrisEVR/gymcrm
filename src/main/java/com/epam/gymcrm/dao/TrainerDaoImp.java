package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.Trainee;
import com.epam.gymcrm.models.Trainer;
import com.epam.gymcrm.models.Training;
import com.epam.gymcrm.models.TrainingType;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Repository
@Transactional
public class TrainerDaoImp implements TrainerDao {

    private static final Logger logger = Logger.getLogger(TrainerDaoImp.class.getName());

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Optional<Trainer> getTrainer(Long id) {
        return Optional.ofNullable(entityManager.find(Trainer.class, id));
    }

    @Override
    public Trainer createTrainer(Trainer trainer, Long specialization) {
        entityManager.persist(trainer);
        return trainer;
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) {
        String queryString = "SELECT t FROM Trainer t WHERE t.username = :username";

        TypedQuery<Trainer> query = entityManager.createQuery(queryString, Trainer.class);
        query.setParameter("username", trainer.getUsername());
        Trainer foundTrainer = query.getSingleResult();

        if(foundTrainer == null){
            return null;
        }

        return entityManager.merge(trainer);
    }

    public List<Trainer> findByFirstnameAndLastname(String firstname, String lastname){
        String queryString = "SELECT u FROM User u WHERE u.firstName = :firstname AND u.lastName = :lastname";
        TypedQuery<Trainer> query = entityManager.createQuery(queryString, Trainer.class);
        query
                .setParameter("firstname", firstname)
                .setParameter("lastname", lastname);

        return query.getResultList();
    };

    public Integer activateDeactivateTrainer(String username, Boolean isActive){
        String queryString = "UPDATE Trainer t SET t.isActive = :isActive " +
                "WHERE t.username = :username";
        Query query = entityManager.createQuery(queryString);

        query
                .setParameter("username", username)
                .setParameter("isActive", isActive);

        return query.executeUpdate();
    }

    public Trainer findByUsername(String username){
        String queryString = "SELECT t FROM Trainer t WHERE t.username = :username";
        TypedQuery<Trainer> query = entityManager.createQuery(queryString, Trainer.class);

        query.setParameter("username", username);

        return query.getSingleResult();
    }

    public List<Long> findAllTrainersId(){
        String queryString = "SELECT t.id FROM Trainer t";
        TypedQuery<Long> query = entityManager.createQuery(queryString, Long.class);

        return query.getResultList();
    }

    public List<Trainer> findByIdsAndName(List<Long> idList, String name){
        String queryString = "SELECT u FROM User u " +
                "WHERE u.id IN :idList AND CONCAT(u.firstName, ' ', u.lastName) LIKE :name";
        TypedQuery<Trainer> query = entityManager.createQuery(queryString, Trainer.class);

        query.setParameter("idList", idList);
        query.setParameter("name", "%" + name + "%");

        return query.getResultList();
    }

    public List<Trainer> getNotAssignedTrainers(String traineeUsername){
        String queryString = "SELECT t1 " +
                "FROM Trainer t1 " +
                "WHERE t1 NOT IN (" +
                "SELECT tt " +
                "FROM Trainee t2 " +
                "JOIN t2.trainers tt " +
                "WHERE t2.username = :traineeUsername) AND" +
                "t1.isActive = true";

        TypedQuery<Trainer> query = entityManager.createQuery(queryString, Trainer.class);
        query.setParameter("traineeUsername", traineeUsername);

        return query.getResultList();
    }

    public List<Trainer> findAllByUsername(List<String> trainerList){
        String queryString = "SELECT t FROM Trainer t WHERE t.username IN :trainerList";

        TypedQuery<Trainer> query = entityManager.createQuery(queryString, Trainer.class);
        query.setParameter("trainerList", trainerList);

        List<Trainer> responseTrainers = query.getResultList();

        logger.info("Result trainers:" + responseTrainers.toString());
        logger.info("Result trainers:" + trainerList.toString());

        return responseTrainers;
    }
}
