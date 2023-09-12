package com.cars.mscars.DTO;

import com.cars.mscars.Model.Pilot;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

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
    @JsonFormat(pattern = "yyyy")
    private Date year;
}
