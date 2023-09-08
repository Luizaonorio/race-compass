package com.races.msraces.Domain.Track.Service.Exceptions;

public class TrackNotFoundException extends RuntimeException {
    public TrackNotFoundException(String id) {
        super("Track with id " + id + " not found");
    }
}