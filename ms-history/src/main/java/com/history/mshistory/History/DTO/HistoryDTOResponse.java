package com.history.mshistory.History.DTO;

import com.history.mshistory.Model.RaceConversion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HistoryDTOResponse {

    private String id;
    private Date date;
    private RaceConversion race;
}
