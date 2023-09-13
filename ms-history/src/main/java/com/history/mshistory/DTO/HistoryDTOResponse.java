package com.history.mshistory.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.history.mshistory.Model.RaceConversion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HistoryDTOResponse {

    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private RaceConversion race;
}
