package com.races.msraces.RabbitMQ;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQMessageProducer {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueRaceFinishResult;

    private String convertOnJson(Object response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(response);
    }

    public void publish(Object response) throws JsonProcessingException {
        var convertJson = convertOnJson(response);
        rabbitTemplate.convertAndSend(queueRaceFinishResult.getName(), convertJson);
    }
}
