package com.epam.gymcrm.services;

import com.epam.gymcrm.dao.TrainingTypeDaoImp;
import com.epam.gymcrm.models.TrainingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingTypeService {
    @Autowired
    TrainingTypeDaoImp trainingTypeDaoImp;

    public List<TrainingType> getTrainingTypes(){
        return trainingTypeDaoImp.getTrainingTypes();
    }
}
