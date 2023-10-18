package com.epam.gymcrm.services;

import com.epam.gymcrm.models.Trainer;
import com.epam.gymcrm.models.Training;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {
    @Value("${gymcrm.queue-name}")
    private String queueName;
    private final JmsTemplate jmsTemplate;
    private final String MESSAGE_KEY_ID = "id";
    private final String MESSAGE_KEY_USERNAME = "username";
    private final String MESSAGE_KEY_FIRST_NAME = "firstName";
    private final String MESSAGE_KEY_LAST_NAME = "lastName";
    private final String MESSAGE_KEY_IS_ACTIVE = "isActive";
    private final String MESSAGE_KEY_TRAINING_DURATION = "trainingDate";
    private final String MESSAGE_KEY_TRAINING_DATE = "trainingDuration";
    private final String MESSAGE_KEY_ADD = "add";

    public ReportService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void addTrainerWorkload(Trainer trainer, Training training) {
        Map<String, Object> messageKeyValues = new HashMap<>();

        messageKeyValues.put(MESSAGE_KEY_ID, trainer.getId());
        messageKeyValues.put(MESSAGE_KEY_USERNAME, trainer.getUsername());
        messageKeyValues.put(MESSAGE_KEY_FIRST_NAME, trainer.getFirstName());
        messageKeyValues.put(MESSAGE_KEY_LAST_NAME, trainer.getLastName());
        messageKeyValues.put(MESSAGE_KEY_IS_ACTIVE, trainer.getActive());
        messageKeyValues.put(MESSAGE_KEY_TRAINING_DATE, training.getTrainingDate().toString());
        messageKeyValues.put(MESSAGE_KEY_TRAINING_DURATION, training.getTrainingDuration());
        messageKeyValues.put(MESSAGE_KEY_ADD, true);

        jmsTemplate.convertAndSend(queueName, messageKeyValues);
    }
}
