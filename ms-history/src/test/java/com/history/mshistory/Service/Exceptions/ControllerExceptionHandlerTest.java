package com.history.mshistory.Service.Exceptions;

import com.history.mshistory.Service.Exceptions.Error.ErrorResponse;
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
    void historyNotFoundException() {
        HistoryNotFoundException ex = new HistoryNotFoundException(ID_EXAMPLE);

        ResponseEntity<ErrorResponse> responseEntity = controllerExceptionHandler.globalExceptionHandler(ex);

        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Race with id " + ID_EXAMPLE + " not found", responseEntity.getBody().getMessage());
    }

}