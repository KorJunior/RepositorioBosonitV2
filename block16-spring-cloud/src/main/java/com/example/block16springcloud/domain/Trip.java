package com.example.block16springcloud.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dto.client.output.ClientOutPutSimple;
import org.example.dto.trip.TripInput;
import org.example.dto.trip.output.TripOutputComplete;
import org.example.dto.trip.output.TripOutputSimple;

import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Trip {
    @Id
    @GeneratedValue
    private int tripId;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    @OneToMany(mappedBy = "trip")
    private List<Client> passenger;
    private String status;

    public Trip(TripInput tripInput) {
        this.origin = tripInput.getOrigin();
        this.destination = tripInput.getDestination();
        this.departureDate = tripInput.getDepartureDate();
        this.arrivalDate = tripInput.getArrivalDate();
        this.status = tripInput.getStatus();
    }



    public TripOutputComplete toTripOutputComplete() {
        return new TripOutputComplete(this.tripId, this.origin, this.destination, this.departureDate, this.arrivalDate, toPassengerOutputSimple(), this.status);
    }

    private List<ClientOutPutSimple> toPassengerOutputSimple() {
        List<ClientOutPutSimple> passengerOutputSimple = new ArrayList<>();

        if (this.passenger == null) {
            return Collections.emptyList();
        }
        for (Client client : this.passenger) {
            passengerOutputSimple.add(client.toClientOutPutSimple());
        }

        return passengerOutputSimple;
    }

    public TripOutputSimple toTripOutputSimple() {
        return new TripOutputSimple(this.tripId, this.origin, this.destination, this.departureDate, this.arrivalDate, this.status);
    }
}
