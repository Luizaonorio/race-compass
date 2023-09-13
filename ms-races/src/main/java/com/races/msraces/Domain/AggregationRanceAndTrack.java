package com.races.msraces.Domain;

import com.races.msraces.Domain.Track.DTO.TrackDTOResponse;
import com.races.msraces.Domain.Track.Entity.Track;
import com.races.msraces.Domain.Track.Service.TrackService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AggregationRanceAndTrack {

    @Autowired
    private TrackService trackService;

    @Autowired
    private ModelMapper mapper;

    public Track existingTrackOnDB(String id_track) {
        TrackDTOResponse trackOnDB = trackService.findTrackById(id_track);
        return mapper.map(trackOnDB, Track.class);
    }
}