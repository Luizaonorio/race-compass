package com.cars.mscars.DTO;

import com.cars.mscars.Model.Pilot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarDTOResponse {

    private Long id;
    private String brand;
    private String model;
    private Pilot pilot;
    private int year;
}
