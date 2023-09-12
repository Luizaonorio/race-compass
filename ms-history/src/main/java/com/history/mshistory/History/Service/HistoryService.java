package com.history.mshistory.History.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.races.msraces.Domain.AggregationRanceAndTrack;
import com.races.msraces.Domain.Race.Cars;
import com.races.msraces.Domain.Race.DTO.RaceDTORequest;
import com.races.msraces.Domain.Race.DTO.RaceDTOResponse;
import com.races.msraces.Domain.Race.Entity.Race;
import com.races.msraces.Domain.Race.Repository.RaceRepository;
import com.races.msraces.Domain.Race.Service.Exceptions.RaceNotFoundException;
import com.races.msraces.Domain.Race.Service.Validation.ValidRace;
import com.races.msraces.Domain.Track.Entity.Track;
import com.races.msraces.Model.CarsConversion;
import com.races.msraces.RabbitMQ.RabbitMQMessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RaceService {

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private Cars cars;

    @Autowired
    private AggregationRanceAndTrack aggregationRanceAndTrack;

    @Autowired
    private ModelMapper mapper;

    private final RabbitMQMessageProducer messageProducer;


    public RaceDTOResponse findRaceById(String id) {
        Race raceEntity = raceRepository.findById(id).orElseThrow(() -> new RaceNotFoundException(id));
        RaceDTOResponse raceResponse = mapper.map(raceEntity, RaceDTOResponse.class);
        return raceResponse;
    }

    public List<RaceDTOResponse> findAllRaces() {
        List<Race> raceListEntity = raceRepository.findAll();
        List<RaceDTOResponse> racesListResponse = raceListEntity.stream().map(race -> mapper.map(race, RaceDTOResponse.class)).toList();
        return racesListResponse;
    }

    public RaceDTOResponse createRace(RaceDTORequest raceDTORequest) throws JsonProcessingException {
        Track track = aggregationRanceAndTrack.existingTrackOnDB(raceDTORequest.getTrack());
        Race raceConversion = mapper.map(raceDTORequest, Race.class);
        raceConversion.setTrack(track);

        List<CarsConversion> positionListCars = ValidRace.RunTheRace(cars.rafflingOffCars());
        raceConversion.setCarsList(positionListCars);

        messageProducer.publish(raceConversion);

        Race raceSaveEntity = raceRepository.save(raceConversion);
        RaceDTOResponse raceResponse = mapper.map(raceSaveEntity, RaceDTOResponse.class);
        return raceResponse;
    }
}
