package com.races.msraces.Domain.Track.Controller;

import com.races.msraces.Domain.Track.Service.TrackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import  com.races.msraces.Domain.Track.DTO.TrackDTORequest;
import  com.races.msraces.Domain.Track.DTO.TrackDTOResponse;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/track")
public class TrackController {

    @Autowired
    private TrackService trackService;

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<TrackDTOResponse> findById(@PathVariable String id) {
        return new ResponseEntity<>(trackService.findTrackById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/get/tracks")
    public ResponseEntity<List<TrackDTOResponse>> findAll() {
        return new ResponseEntity<>(trackService.findAllTracks(), HttpStatus.OK);
    }

    @PostMapping(value = "/post")
    public ResponseEntity<TrackDTOResponse> create(@Valid @RequestBody TrackDTORequest trackDTORequest) {
        return new ResponseEntity<>(trackService.createTrack(trackDTORequest), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<TrackDTOResponse> update(@Valid @RequestBody TrackDTORequest trackDTORequest, @PathVariable String id) {
        return new ResponseEntity<>(trackService.updateTrack(trackDTORequest, id), HttpStatus.OK);
    }
}
