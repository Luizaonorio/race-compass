package com.history.mshistory.RabbitMQ;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.history.mshistory.Service.HistoryService;
import com.history.mshistory.Model.RaceConversion;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
@RequiredArgsConstructor
public class RabbitMQMessageConsumer {

    private final HistoryService historyService;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "RACE-RESULT")
    private void receivesAndConsumesRaceResults(String raceResult) throws JsonProcessingException {
        RaceConversion race = objectMapper.readValue(raceResult, RaceConversion.class);
        historyService.saveRaceResultOnDB(race);
    }
}