package com.cars.mscars.Service.Exceptions.Error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ValidationErrorResponse {

    private int statusCode;
    private LocalDateTime timestamp;
    private String message;
    private List<ValidationError> errors;
}
