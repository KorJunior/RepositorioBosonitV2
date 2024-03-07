package com.example.block16ticket.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.example.dto.trip.output.TripOutputComplete;
import org.example.dto.tripShop.TripShopOutput;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class TripShop {
    @Id
    @GeneratedValue
    private int id;
    private int quantity;

    public TripShop(TripOutputComplete message) {
        this.id = message.getId();
        this.quantity = message.getQuantityPassenger();
    }

    public TripShop(TripShopOutput message) {
        this.id = message.getIdTrip();
        this.quantity = message.getQuantity();
    }

    public TripShopOutput toTripShopOutput() {
        return new TripShopOutput(this.id, this.quantity);
    }
}
