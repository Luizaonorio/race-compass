package com.cars.mscars.Service.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ErrorResponse {

    private Integer statusCode;
    private LocalDateTime timestamp;
    private String message;

}
