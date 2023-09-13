package com.races.msraces.Domain.Track.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "track")
public class Track {

    @Id
    private String id;
    private String name;
    private String country;
}
