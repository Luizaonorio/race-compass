package com.cars.mscars.Service.Exceptions.Error;

public class AlreadyRegisteredException extends RuntimeException {
    public AlreadyRegisteredException(String type) {
        super(type + " already registered!");
    }
}