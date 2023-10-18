package com.epam.gymcrm.services;

import com.epam.gymcrm.dao.TrainingTypeRepository;
import com.epam.gymcrm.models.TrainingType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingTypeService {
    private final TrainingTypeRepository trainingTypeRepository;

    public TrainingTypeService(TrainingTypeRepository trainingTypeRepository) {
        this.trainingTypeRepository = trainingTypeRepository;
    }

    public List<TrainingType> getTrainingTypes() {
        return trainingTypeRepository.findAll();
    }
}
