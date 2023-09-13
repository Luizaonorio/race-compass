package com.races.msraces.Domain.Race.Service;

import com.races.msraces.Domain.AggregationRanceAndTrack;
import com.races.msraces.Domain.Race.Cars;
import com.races.msraces.Domain.Race.DTO.RaceDTORequest;
import com.races.msraces.Domain.Race.DTO.RaceDTOResponse;
import com.races.msraces.Domain.Race.Entity.Race;
import com.races.msraces.Domain.Race.Repository.RaceRepository;
import com.races.msraces.RabbitMQ.RabbitMQMessageProducer;
import com.races.msraces.Utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RaceServiceTest {
    @InjectMocks
    private RaceService raceService;

    @Mock
    private AggregationRanceAndTrack aggregationRanceAndTrack;

    @Mock
    private Cars cars;

    @Mock
    private RabbitMQMessageProducer messageProducer;

    @Mock
    private RaceRepository raceRepository;

    @Spy
    private ModelMapper mapper;

    private static final String RACEDTO = "Payload/Race/RaceDTORequest.json";
    private static final String RACE = "Payload/Race/Race.json";

    @Test
    void findRaceById() throws IOException {
        Race race = JsonUtils.getObjectFromFile(RACE, Race.class);
        RaceDTOResponse raceDTOResponse = JsonUtils.getObjectFromFile(RACE, RaceDTOResponse.class);

        when(raceRepository.findById(anyString())).thenReturn(Optional.ofNullable(race));
        RaceDTOResponse response = raceService.findRaceById(anyString());

        assertNotNull(response);
        assertEquals(raceDTOResponse, response);
    }

    @Test
    void findAllRaces() throws IOException {
        Race race = JsonUtils.getObjectFromFile(RACE, Race.class);
        RaceDTOResponse raceDTOResponse = JsonUtils.getObjectFromFile(RACE, RaceDTOResponse.class);

        when(raceRepository.findAll()).thenReturn(List.of(race));
        List<RaceDTOResponse> response = raceService.findAllRaces();

        assertNotNull(response);
        assertEquals(raceDTOResponse, response.get(0));
        assertEquals(raceDTOResponse.getName(), response.get(0).getName());
    }

    @Test
    void createRace() throws IOException {
        Race race = JsonUtils.getObjectFromFile(RACE, Race.class);
        RaceDTOResponse raceDTOResponse = JsonUtils.getObjectFromFile(RACE, RaceDTOResponse.class);
        RaceDTORequest raceDTORequest = JsonUtils.getObjectFromFile(RACEDTO, RaceDTORequest.class);

        when(raceRepository.save(any())).thenReturn(race);
        RaceDTOResponse response = raceService.createRace(raceDTORequest);

        assertNotNull(response);
        assertEquals(raceDTOResponse, response);
    }
}