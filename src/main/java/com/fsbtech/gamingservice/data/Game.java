package com.fsbtech.gamingservice.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    private String id;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate creationDate;
    private Boolean active;

}
