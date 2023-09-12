package com.races.msraces.Domain.Race.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RaceDTORequest {

    @NotBlank
    private String name;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @NotBlank
    private String track;
}
