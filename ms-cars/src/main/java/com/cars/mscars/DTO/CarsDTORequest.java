package com.cars.mscars.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CarDTORequest {

    @NotBlank
    @NotNull
    private String brand;
    @NotBlank
    @NotNull
    private String model;
    @NotBlank
    @NotNull
    private Object pilot;
    @NotBlank
    @NotNull
    @Max(4)
    @Min(4)
    private int year;
}
