package com.example.block16ticket.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue
    private int idTicket;
    private int idTrip;
    private int passengerId;
    private String passengerName;
    private String passengerLastName;
    private String email;
    private String tripOrigin;
    private String tripDestination;
    private String departureDate;
    private String arrivalDate;
}
