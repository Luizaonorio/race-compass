package com.races.msraces.Domain.Race.Service.Exceptions;

public class RaceNotFoundException extends RuntimeException {
    public RaceNotFoundException(String id) {
        super("Track with id " + id + " not found");
    }
}