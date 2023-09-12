package com.history.mshistory.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class RaceConversion {

    private String id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private TrackConversion track;
    private List<CarsConversion> carsList;
}
