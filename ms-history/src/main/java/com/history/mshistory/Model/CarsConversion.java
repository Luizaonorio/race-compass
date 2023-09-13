package com.history.mshistory.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarsConversion {

    private String id;
    private int position;
    private String brand;
    private String model;
    private PilotConversion pilot;
    private int velocity;
    @JsonFormat(pattern = "yyyy")
    private Date year;
}
