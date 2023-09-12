package com.races.msraces.Domain;

import com.races.msraces.Domain.Race.Service.RaceService;
import com.races.msraces.Domain.Track.Entity.Track;
import com.races.msraces.Domain.Track.Service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AggregationRanceAndTrack {

    @Autowired
    private TrackService trackService;

    @Autowired
    private RaceService raceService;

    public static void validateExistingTrackOnDB(List<Track> trackListonDB, String id_track) {



    }
}
