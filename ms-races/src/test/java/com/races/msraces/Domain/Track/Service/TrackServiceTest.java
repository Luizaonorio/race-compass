package com.races.msraces.Domain.Track.Service;

import com.races.msraces.Domain.Track.DTO.TrackDTORequest;
import com.races.msraces.Domain.Track.DTO.TrackDTOResponse;
import com.races.msraces.Domain.Track.Entity.Track;
import com.races.msraces.Domain.Track.Repository.TrackRepository;
import com.races.msraces.Domain.Track.Service.Exceptions.TrackNotFoundException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrackServiceTest {

    @InjectMocks
    private TrackService trackService;

    @Mock
    private TrackRepository trackRepository;

    @Spy
    private ModelMapper mapper;

    private static final String TRACKDTO = "Payload/Track/TackDTORequest.json";
    private static final String TRACK = "Payload/Track/Track.json";

    @Test
    void findTrackById() throws IOException {
        Track track = JsonUtils.getObjectFromFile(TRACK, Track.class);
        TrackDTOResponse trackDTOResponse = JsonUtils.getObjectFromFile(TRACK, TrackDTOResponse.class);
        when(trackRepository.findById(anyString())).thenReturn(Optional.ofNullable(track));
        TrackDTOResponse response = trackService.findTrackById(anyString());

        assertNotNull(response);
        assertEquals(trackDTOResponse, response);
    }

    @Test
    void findTrackById_ERROR_TrackNotFoundException() {
        when(trackRepository.findById(anyString())).thenReturn(Optional.empty());
        assertThrows(TrackNotFoundException.class, () -> trackService.findTrackById(anyString()));
    }

    @Test
    void findAllTracks() throws IOException {
        Track track = JsonUtils.getObjectFromFile(TRACK, Track.class);
        TrackDTOResponse classDTOResponse = JsonUtils.getObjectFromFile(TRACK, TrackDTOResponse.class);

        when(trackRepository.findAll()).thenReturn(List.of(track));
        List<TrackDTOResponse> response = trackService.findAllTracks();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(classDTOResponse, response.get(0));
    }

    @Test
    void createTrack() throws IOException {
        TrackDTORequest trackDTORequest = JsonUtils.getObjectFromFile(TRACKDTO, TrackDTORequest.class);
        Track cars = JsonUtils.getObjectFromFile(TRACK, Track.class);
        TrackDTOResponse trackDTOResponse = JsonUtils.getObjectFromFile(TRACK, TrackDTOResponse.class);

        when(trackRepository.save(any())).thenReturn(cars);
        TrackDTOResponse response = trackService.createTrack(trackDTORequest);

        assertNotNull(response);
        assertEquals(trackDTOResponse, response);
    }

    @Test
    void updateTrack() throws IOException {
        TrackDTORequest trackDTORequest = JsonUtils.getObjectFromFile(TRACKDTO, TrackDTORequest.class);
        Track track = JsonUtils.getObjectFromFile(TRACK, Track.class);
        TrackDTOResponse carsDTOResponse = JsonUtils.getObjectFromFile(TRACK, TrackDTOResponse.class);

        when(trackRepository.findById(anyString())).thenReturn(Optional.ofNullable(track));

        when(trackRepository.save(track)).thenReturn(track);
        TrackDTOResponse response = trackService.updateTrack(trackDTORequest, anyString());

        assertNotNull(response);
        assertEquals(carsDTOResponse, response);
    }

    @Test
    void updateTrack_ERROR_TrackNotFoundException() throws IOException {
        TrackDTORequest trackDTORequest = JsonUtils.getObjectFromFile(TRACKDTO, TrackDTORequest.class);
        when(trackRepository.findById(anyString())).thenReturn(Optional.empty());
        assertThrows(TrackNotFoundException.class, () -> trackService.updateTrack(trackDTORequest, anyString()));
    }
}