package com.races.msraces.Domain.Race.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.races.msraces.Domain.Track.Entity.Track;
import com.races.msraces.Model.CarsConversion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RaceDTOResponse {

    private String id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Track track;
    private List<CarsConversion> carsList;
}
