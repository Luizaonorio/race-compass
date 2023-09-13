package com.cars.mscars.Service.Exceptions;

import com.cars.mscars.Service.Exceptions.Error.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ControllerExceptionHandlerTest {

    @InjectMocks
    private ControllerExceptionHandler controllerExceptionHandler;
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
    void testCarNotFoundException() {
        CarNotFoundException ex = new CarNotFoundException(ID_EXAMPLE);

        ResponseEntity<ErrorResponse> responseEntity = controllerExceptionHandler.carNotFoundException(ex);

        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Car with id " + ID_EXAMPLE + " not found", responseEntity.getBody().getMessage());
    }

    @Test
    void alreadyRegisteredExceptionCar() {
        AlreadyRegisteredExceptionCar ex = new AlreadyRegisteredExceptionCar();

        ResponseEntity<ErrorResponse> responseEntity = controllerExceptionHandler.alreadyRegisteredExceptionCar(ex);

        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Car already registered!", responseEntity.getBody().getMessage());
    }

    @Test
    void alreadyRegisteredExceptionPilot() {
        AlreadyRegisteredExceptionPilot ex = new AlreadyRegisteredExceptionPilot();

        ResponseEntity<ErrorResponse> responseEntity = controllerExceptionHandler.alreadyRegisteredExceptionPilot(ex);

        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Pilot already registered!", responseEntity.getBody().getMessage());
    }
}