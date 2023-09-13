package com.cars.mscars.Service.Exceptions;

public class AlreadyRegisteredExceptionPilot extends RuntimeException {
    public AlreadyRegisteredExceptionPilot() {
        super("Pilot already registered!");
    }
}