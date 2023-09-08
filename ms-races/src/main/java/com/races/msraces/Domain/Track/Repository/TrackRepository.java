package com.races.msraces.Domain.Track.Repository;


import com.races.msraces.Domain.Track.Entity.Track;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrackRepository extends MongoRepository<Track, String> {
}
