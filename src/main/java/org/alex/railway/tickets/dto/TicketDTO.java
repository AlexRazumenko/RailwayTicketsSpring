package org.alex.railway.tickets.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class TicketDTO {
    private Long id;
    private LocalDate departureDate;
    private int place;
    private  boolean occupied;
    private LocalTime arriveTime;
    private LocalTime departTime;
    private int trainNumber;
    private int price;
    private String departStationName;
    private String arriveStationName;
    private String email;
    private String passengerFirstName;
    private String passengerLastName;
}
