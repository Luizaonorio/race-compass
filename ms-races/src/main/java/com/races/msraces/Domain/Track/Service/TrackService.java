package com.races.msraces.Domain.Track.Service;

import com.races.msraces.Domain.Track.DTO.TrackDTORequest;
import com.races.msraces.Domain.Track.DTO.TrackDTOResponse;
import com.races.msraces.Domain.Track.Entity.Track;
import com.races.msraces.Domain.Track.Repository.TrackRepository;
import com.races.msraces.Domain.Track.Service.Exceptions.TrackNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private ModelMapper mapper;

    public TrackDTOResponse findTrackById(String id) {
        Track trackEntity = trackRepository.findById(id).orElseThrow(() -> new TrackNotFoundException(id));
        TrackDTOResponse trackResponse = mapper.map(trackEntity, TrackDTOResponse.class);
        return trackResponse;
    }

    public List<TrackDTOResponse> findAllTracks() {
        List<Track> trackListEntity = trackRepository.findAll();
        List<TrackDTOResponse> tracksListResponse = trackListEntity.stream().map(track -> mapper.map(track, TrackDTOResponse.class)).toList();
        return tracksListResponse;
    }

    public TrackDTOResponse createTrack(TrackDTORequest trackDTORequest) {
        Track trackConversion = mapper.map(trackDTORequest, Track.class);
        Track trackSaveEntity = trackRepository.save(trackConversion);
        TrackDTOResponse trackResponse = mapper.map(trackSaveEntity, TrackDTOResponse.class);
        return trackResponse;
    }

    public TrackDTOResponse updateTrack(TrackDTORequest trackDTORequest, String id) {
        Track track = trackRepository.findById(id).orElseThrow(() -> new TrackNotFoundException(id));
        track.setName(trackDTORequest.getName());
        track.setCountry(trackDTORequest.getCountry());

        trackRepository.save(track);
        TrackDTOResponse trackResponse = mapper.map(track, TrackDTOResponse.class);
        return trackResponse;
    }
}
