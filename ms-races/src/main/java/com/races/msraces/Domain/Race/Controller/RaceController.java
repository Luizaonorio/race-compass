package com.races.msraces.Domain.Race.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.races.msraces.Domain.Race.DTO.RaceDTORequest;
import com.races.msraces.Domain.Race.DTO.RaceDTOResponse;
import com.races.msraces.Domain.Race.Service.RaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/races")
public class RaceController {

    @Autowired
    private RaceService raceService;

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<RaceDTOResponse> findById(@PathVariable String id) {
        return new ResponseEntity<>(raceService.findRaceById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<RaceDTOResponse>> findAll() {
        return new ResponseEntity<>(raceService.findAllRaces(), HttpStatus.OK);
    }

    @PostMapping(value = "/post")
    public ResponseEntity<RaceDTOResponse> create(@Valid @RequestBody RaceDTORequest racesDTORequest) throws JsonProcessingException {
        return new ResponseEntity<>(raceService.createRace(racesDTORequest), HttpStatus.CREATED);
    }
}
