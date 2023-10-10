package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.Trainee;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Repository
@Transactional
public class TraineeDaoImp implements TraineeDao {
    private static final Logger logger = Logger.getLogger(TraineeDaoImp.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<Trainee> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Trainee.class, id));
    }

    @Override
    public Trainee createTrainee(Trainee trainee) {
        entityManager.persist(trainee);
        return trainee;
    }

    @Override
    public Trainee updateTrainee(Trainee trainee){
        String queryString = "SELECT t FROM Trainee t WHERE t.username = :username";
        logger.info("results--------: " + trainee);

        TypedQuery<Trainee> query = entityManager.createQuery(queryString, Trainee.class);
        query.setParameter("username", trainee.getUsername());
        Trainee foundTrainee = query.getSingleResult();

        if(foundTrainee == null){
            return null;
        }

        Trainee trainee1 = entityManager.merge(trainee);

        logger.info("results: " + trainee1.toString());

        return trainee1;
    }

    @Override
    public Integer deleteTraineeByUsername(String username) {
        Query query = entityManager.createQuery("DELETE FROM User u WHERE u.username = :username");
        query.setParameter("username", username);

        return query.executeUpdate();
    }

    public List<Trainee> findByFirstnameAndLastname(String firstname, String lastname){
        String queryString = "SELECT u FROM User u WHERE u.firstName = :firstname AND u.lastName = :lastname";
        TypedQuery<Trainee> query = entityManager.createQuery(queryString, Trainee.class);
        query
                .setParameter("firstname", firstname)
                .setParameter("lastname", lastname);

        return query.getResultList();
    };

    public Integer activateDeactivateTrainee(String username, Boolean isActive){
        String queryString = "UPDATE Trainee t SET t.isActive = :isActive " +
                "WHERE t.username = :username";
        Query query = entityManager.createQuery(queryString);

        query
                .setParameter("isActive", isActive)
                .setParameter("username", username);

        return query.executeUpdate();
    }

    public Trainee findByUsername(String username){
        String queryString = "SELECT t " +
                "FROM Trainee t " +
                "WHERE t.username = :username";
        TypedQuery<Trainee> query = entityManager.createQuery(queryString, Trainee.class);

        query.setParameter("username", username);

        return query.getSingleResult();
    }

    public List<Trainee> findAllTrainees(){
        String queryString = "SELECT t " +
                "FROM Trainee t ";
        TypedQuery<Trainee> query = entityManager.createQuery(queryString, Trainee.class);

        return query.getResultList();
    }

    public List<Long> findAllTraineesId(){
        String queryString = "SELECT t.id FROM Trainee t";
        TypedQuery<Long> query = entityManager.createQuery(queryString, Long.class);

        return query.getResultList();
    }

    public List<Trainee> findByIdsAndName(List<Long> idList, String name){
        String queryString = "SELECT t FROM Trainee t " +
                "WHERE t.id IN :idList AND CONCAT(t.firstName, ' ', t.lastName) LIKE :name";
        TypedQuery<Trainee> query = entityManager.createQuery(queryString, Trainee.class);

        query.setParameter("idList", idList);
        query.setParameter("name", "%" + name + "%");

        return query.getResultList();
    }

    public void updateTrainerList(Trainee trainee){
        entityManager.persist(trainee);
    }

}
