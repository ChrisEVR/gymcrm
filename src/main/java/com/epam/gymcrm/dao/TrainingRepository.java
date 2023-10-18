package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.Training;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TrainingRepository extends JpaRepository<Training, Long> {
    @Query("SELECT t " +
            "FROM Training t " +
            "WHERE t.trainer.id = :idUser " +
            "AND (:name is null OR CONCAT(t.trainee.firstName, ' ', t.trainee.lastName) " +
            "LIKE :name) " +
            "AND (:periodFrom is null OR t.trainingDate >= :periodFrom) " +
            "AND (:periodTo is null OR t.trainingDate <= :periodTo)")
    List<Training> findByTrainingDateBetweenAndTrainerIdAndTrainingName(
            @Param("idUser") Long id,
            @Param("name") String name,
            @Param("periodFrom") Date periodFrom,
            @Param("periodTo") Date periodTo
    );

    @Query("SELECT t " +
            "FROM Training t " +
            "WHERE t.trainee.id = :idUser " +
            "AND (:name is null OR CONCAT(t.trainer.firstName, ' ', t.trainer.lastName) " +
            "LIKE :name) " +
            "AND (:periodFrom is null OR t.trainingDate >= :periodFrom) " +
            "AND (:periodTo is null OR t.trainingDate <= :periodTo)")
    List<Training> findByTrainingDateBetweenAndTraineeIdAndTrainingName(
            @Param("idUser") Long id,
            @Param("name") String name,
            @Param("periodFrom") Date periodFrom,
            @Param("periodTo") Date periodTo
    );

}
