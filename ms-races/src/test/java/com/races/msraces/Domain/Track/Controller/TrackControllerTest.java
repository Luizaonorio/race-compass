package com.races.msraces.Domain.Track.Controller;

import com.races.msraces.Domain.Track.DTO.TrackDTORequest;
import com.races.msraces.Domain.Track.DTO.TrackDTOResponse;
import com.races.msraces.Domain.Track.Service.TrackService;
import com.races.msraces.Utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.races.msraces.Utils.JsonUtils.objectMapper;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrackController.class)
class TrackControllerTest {

    @MockBean
    private TrackService trackService;

    @Autowired
    private MockMvc mockMvc;

    private static final String TRACKDTO = "Payload/Track/TackDTORequest.json";
    private static final String TRACK = "Payload/Track/Track.json";
    private static final String ID_TRACK = "2";
    @Test
    void findById() throws Exception {
        TrackDTOResponse car = JsonUtils.getObjectFromFile(TRACK, TrackDTOResponse.class);

        when(trackService.findTrackById(ID_TRACK)).thenReturn(car);

        mockMvc.perform(get("/api/v1/track/get/{id}", ID_TRACK))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID_TRACK))
                .andExpect(content().json(objectMapper.writeValueAsString(car)));

        verify(trackService, times(1)).findTrackById(ID_TRACK);
    }

    @Test
    void findAll() throws Exception {
        TrackDTOResponse track = JsonUtils.getObjectFromFile(TRACK, TrackDTOResponse.class);

        when(trackService.findAllTracks()).thenReturn(List.of(track));

        mockMvc.perform(get("/api/v1/track/get/tracks"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(track))));

        verify(trackService, times(1)).findAllTracks();
    }

    @Test
    void create() throws Exception {
        TrackDTORequest trackDTORequest = JsonUtils.getObjectFromFile(TRACKDTO, TrackDTORequest.class);
        TrackDTOResponse track = JsonUtils.getObjectFromFile(TRACK, TrackDTOResponse.class);

        when(trackService.createTrack(trackDTORequest)).thenReturn(track);

        mockMvc.perform(post("/api/v1/track/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trackDTORequest)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(track)));

        verify(trackService, times(1)).createTrack(trackDTORequest);
    }

    @Test
    void update() throws Exception {
        TrackDTORequest trackDTORequest = JsonUtils.getObjectFromFile(TRACKDTO, TrackDTORequest.class);
        TrackDTOResponse track = JsonUtils.getObjectFromFile(TRACK, TrackDTOResponse.class);

        when(trackService.updateTrack(trackDTORequest, ID_TRACK)).thenReturn(track);

        mockMvc.perform(put("/api/v1/track/update/{id}", ID_TRACK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trackDTORequest)))
                .andExpect(jsonPath("$.id").value(ID_TRACK))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(track)));

        verify(trackService, times(1)).updateTrack(trackDTORequest, ID_TRACK);
    }
}