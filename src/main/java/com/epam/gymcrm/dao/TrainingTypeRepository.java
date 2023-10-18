package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.TrainingType;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface TrainingTypeRepository extends JpaRepository<TrainingType, Long> {
    TrainingType findByTrainingTypeName(String trainingTypeName);
}
