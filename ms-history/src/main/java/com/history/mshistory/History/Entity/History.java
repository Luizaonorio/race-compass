package com.history.mshistory.History.Entity;


import com.history.mshistory.Model.RaceConversion;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "ms-history")
public class History {

    @Id
    private String id;
    private Date date;
    private RaceConversion race;
}
