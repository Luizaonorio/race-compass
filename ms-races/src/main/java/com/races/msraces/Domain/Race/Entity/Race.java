package com.races.msraces.Domain.Race.Entity;


import com.races.msraces.Domain.Track.Entity.Track;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DateFormat;
import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "ms-race")
public class RaceEntity {

    @Id
    private String id;
    private DateFormat date;
    private Track track_id;
    private List<Car> carsList;
}
