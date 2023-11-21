package com.epam.gymcrm.services;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.epam.gymcrm.models.Trainer;
import com.epam.gymcrm.models.Training;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class ReportService {
    private static final Logger logger = Logger.getLogger(ReportService.class.getName());
    private final String MESSAGE_KEY_ID = "id";
    private final String MESSAGE_KEY_USERNAME = "username";
    private final String MESSAGE_KEY_FIRST_NAME = "firstName";
    private final String MESSAGE_KEY_LAST_NAME = "lastName";
    private final String MESSAGE_KEY_IS_ACTIVE = "isActive";
    private final String MESSAGE_KEY_TRAINING_DATE = "trainingDate";
    private final String MESSAGE_KEY_TRAINING_DURATION = "trainingDuration";
    private final String MESSAGE_KEY_ADD = "add";
    @Value("${aws.sqs.url}")
    private String queueUrl;

    public void addTrainerWorkload(Trainer trainer, Training training) throws JsonProcessingException {
        Map<String, Object> messageKeyValues = new HashMap<>();
        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        String messageGroupId = "1";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload;

        messageKeyValues.put(MESSAGE_KEY_ID, trainer.getId());
        messageKeyValues.put(MESSAGE_KEY_USERNAME, trainer.getUsername());
        messageKeyValues.put(MESSAGE_KEY_FIRST_NAME, trainer.getFirstName());
        messageKeyValues.put(MESSAGE_KEY_LAST_NAME, trainer.getLastName());
        messageKeyValues.put(MESSAGE_KEY_IS_ACTIVE, trainer.getActive());
        messageKeyValues.put(MESSAGE_KEY_TRAINING_DATE, training.getTrainingDate().toString());
        messageKeyValues.put(MESSAGE_KEY_TRAINING_DURATION, training.getTrainingDuration());
        messageKeyValues.put(MESSAGE_KEY_ADD, true);

        jsonPayload = objectMapper.writeValueAsString(messageKeyValues);
        logger.info("jsonPayload:" + jsonPayload);

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                        .withQueueUrl(queueUrl)
                        .withMessageBody(jsonPayload)
                        .withMessageGroupId(messageGroupId);

        sqs.sendMessage(sendMessageRequest);
    }
}
