package com.cars.mscars.DTO;

import com.cars.mscars.Model.Pilot;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarsDTORequest {

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @Valid
    private Pilot pilot;

    @NotNull
    private int year;
}
