package com.cars.mscars.Service.Exceptions;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(String id) {
        super("Car with id " + id + " not found");
    }
}