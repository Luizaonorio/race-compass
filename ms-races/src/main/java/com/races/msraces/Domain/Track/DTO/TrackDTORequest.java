package com.races.msraces.Domain.Track.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrackDTORequest {

    @NotBlank
    private String name;
    @NotBlank
    private  String country;
}
