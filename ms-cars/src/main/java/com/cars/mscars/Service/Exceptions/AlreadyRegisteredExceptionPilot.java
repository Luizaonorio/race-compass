package com.cars.mscars.Service.Exceptions.Error;

public class AlreadyRegisteredExceptionPilot extends RuntimeException {
    public AlreadyRegisteredExceptionPilot() {
        super("Pilot already registered!");
    }
}