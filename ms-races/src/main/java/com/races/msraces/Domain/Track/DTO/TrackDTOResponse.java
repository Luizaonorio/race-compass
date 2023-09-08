package com.races.msraces.Domain.Track.DTO;

import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrackDTOResponse {

    private String id;
    private String name;
    private  String country;
}
