package org.example.dto.trip.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dto.client.output.ClientOutPutSimple;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripOutputComplete {
    private int id;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private List<ClientOutPutSimple> passenger;
    private String status;
}
