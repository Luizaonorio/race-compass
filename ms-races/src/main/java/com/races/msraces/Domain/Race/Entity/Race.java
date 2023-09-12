package com.races.msraces.Domain.Race.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.races.msraces.Domain.Track.Entity.Track;
import com.races.msraces.Model.CarsConversion;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "ms-race")
public class Race {

    @Id
    private String id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Track track;
    private List<CarsConversion> carsList;
}
