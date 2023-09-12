package com.races.msraces.Domain.Race.Service.Exceptions.Error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationError {

    private final String message;
    private final String field;
}
