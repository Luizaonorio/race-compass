package com.cars.mscars.Entity;

import com.cars.mscars.Model.Pilot;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "ms-cars")
public class Cars {

    @Id
    private String id;
    private String brand;
    private String model;
    private Pilot pilot;
    @JsonFormat(pattern = "yyyy")
    private Date year;
}
