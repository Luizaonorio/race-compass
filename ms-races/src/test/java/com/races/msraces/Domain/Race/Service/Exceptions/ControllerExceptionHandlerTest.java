package com.races.msraces.Domain.Race.Service.Exceptions;

import com.races.msraces.Domain.Race.Service.Exceptions.Error.ErrorResponse;
import com.races.msraces.Domain.Track.Service.Exceptions.TrackNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ControllerExceptionHandlerTest {

    @InjectMocks
    private  ControllerExceptionHandler controllerExceptionHandler;
    private static final String ID_EXAMPLE = "2";

    @Test
    void globalExceptionHandler() {
        Exception ex = new Exception("Test exception");

        ResponseEntity<ErrorResponse> responseEntity = controllerExceptionHandler.globalExceptionHandler(ex);

        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Test exception", responseEntity.getBody().getMessage());
    }

    @Test
    void raceNotFoundException() {
        RaceNotFoundException ex = new RaceNotFoundException(ID_EXAMPLE);

        ResponseEntity<ErrorResponse> responseEntity = controllerExceptionHandler.raceNotFoundException(ex);

        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Race with id " + ID_EXAMPLE +" not found", responseEntity.getBody().getMessage());
    }

    @Test
    void trackNotFoundException() {
        TrackNotFoundException ex = new TrackNotFoundException(ID_EXAMPLE);

        ResponseEntity<ErrorResponse> responseEntity = controllerExceptionHandler.trackNotFoundException(ex);

        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Track with id " + ID_EXAMPLE +" not found", responseEntity.getBody().getMessage());
    }
}