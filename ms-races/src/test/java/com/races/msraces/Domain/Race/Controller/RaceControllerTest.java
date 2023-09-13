package com.races.msraces.Domain.Race.Controller;

import com.races.msraces.Domain.Race.DTO.RaceDTORequest;
import com.races.msraces.Domain.Race.DTO.RaceDTOResponse;
import com.races.msraces.Domain.Race.Service.RaceService;
import com.races.msraces.Utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.races.msraces.Utils.JsonUtils.objectMapper;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RaceController.class)
class RaceControllerTest {

    @MockBean
    private RaceService raceService;

    @Autowired
    private MockMvc mockMvc;

    private static final String RACEDTO = "Payload/Race/RaceDTORequest.json";
    private static final String RACE = "Payload/Race/Race.json";
    private static final String ID_RACE = "2";

    @Test
    void findById() throws Exception {
        RaceDTOResponse race = JsonUtils.getObjectFromFile(RACE, RaceDTOResponse.class);

        when(raceService.findRaceById(ID_RACE)).thenReturn(race);

        mockMvc.perform(get("/api/v1/races/get/{id}", ID_RACE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID_RACE))
                .andExpect(content().json(objectMapper.writeValueAsString(race)));

        verify(raceService, times(1)).findRaceById(ID_RACE);
    }

    @Test
    void findAll() throws Exception {
        RaceDTOResponse race = JsonUtils.getObjectFromFile(RACE, RaceDTOResponse.class);

        when(raceService.findAllRaces()).thenReturn(List.of(race));

        mockMvc.perform(get("/api/v1/races/get"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(race))));

        verify(raceService, times(1)).findAllRaces();
    }

    @Test
    void create() throws Exception {
        RaceDTOResponse race = JsonUtils.getObjectFromFile(RACE, RaceDTOResponse.class);
        RaceDTORequest raceDTORequest = JsonUtils.getObjectFromFile(RACEDTO, RaceDTORequest.class);

        when(raceService.createRace(raceDTORequest)).thenReturn(race);

        mockMvc.perform(post("/api/v1/races/post")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(raceDTORequest)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(race)));

        verify(raceService, times(1)).createRace(raceDTORequest);
    }
}