package com.races.msraces.Domain.Race.Repository;

import com.races.msraces.Domain.Race.Entity.Race;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RaceRepository extends MongoRepository<Race, String> {
}
