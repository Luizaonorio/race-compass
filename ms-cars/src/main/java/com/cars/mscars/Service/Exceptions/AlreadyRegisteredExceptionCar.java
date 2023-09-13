package com.cars.mscars.Service.Exceptions;

public class AlreadyRegisteredExceptionCar extends RuntimeException {
    public AlreadyRegisteredExceptionCar() {
        super("Car already registered!");
    }
}