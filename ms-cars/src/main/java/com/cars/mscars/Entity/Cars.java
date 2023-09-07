package com.cars.mscars.Entity;

import com.cars.mscars.Model.Pilot;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private int year;
}
