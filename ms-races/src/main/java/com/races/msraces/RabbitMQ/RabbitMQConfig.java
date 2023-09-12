package com.races.msraces.RabbitMQ;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class RabbitMQConfig {
    @Bean
    public Queue queueRaceFinishResult() {
        return new Queue("RACE-RESULT", true);
    }
}
