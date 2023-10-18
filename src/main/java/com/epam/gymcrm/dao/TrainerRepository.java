package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.Trainer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Trainer findByUsername(String username);
    @Query("SELECT t FROM Trainer t WHERE t.username IN :usernames")
    List<Trainer> findByUsernamesInList(@Param("usernames") List<String> usernames);

    @Query("SELECT t1 " +
            "FROM Trainer t1 " +
            "WHERE t1 NOT IN (" +
            "SELECT tt " +
            "FROM Trainee t2 " +
            "JOIN t2.trainers tt " +
            "WHERE t2.username = :traineeUsername) AND " +
            "t1.isActive = true")
    List<Trainer> findNotAssignedTrainers(@Param("traineeUsername") String username);
}
