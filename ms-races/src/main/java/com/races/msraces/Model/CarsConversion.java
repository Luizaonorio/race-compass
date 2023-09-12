package com.cars.mscars.DTO;

import com.cars.mscars.Model.Pilot;
import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarsDTOResponse {

    private String id;
    private String brand;
    private String model;

    private Pilot pilot;
    private int year;
}
