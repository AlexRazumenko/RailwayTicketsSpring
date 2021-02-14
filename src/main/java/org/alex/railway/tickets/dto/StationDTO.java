package org.alex.railway.tickets.dto;


import lombok.*;
import org.alex.railway.tickets.entity.Station;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class StationDTO {

    private Station departStation;
    private Station arriveStation;


}
