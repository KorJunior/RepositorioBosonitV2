package org.example.dto.trip.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripOutputSimple {
    private int id;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private String status;
}
