package org.example.dto.ticket;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TicketOutput {
        private int idTrip;
        private int passengerId;
        private String passengerName;
        private String passengerLastName;
        private String email;
        private String tripOrigin;
        private String tripDestination;
        private Date departureDate;
        private Date arrivalDate;
}
