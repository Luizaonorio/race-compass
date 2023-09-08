package com.races.msraces.Domain.Track.Service.Exceptions.Error;

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
