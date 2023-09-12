package com.cars.mscars.DTO;

import com.cars.mscars.Model.Pilot;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @JsonFormat(pattern = "yyyy")
    private Date year;
}
