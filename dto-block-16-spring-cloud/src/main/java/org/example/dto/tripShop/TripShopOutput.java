package org.example.dto.tripShop;

import lombok.*;
import org.example.dto.trip.output.TripOutputComplete;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TripShopOutput {
        private int idTrip;
        private int quantity;

        public TripShopOutput(TripOutputComplete tripOutputComplete) {
                this.idTrip = tripOutputComplete.getId();
                this.quantity = tripOutputComplete.getQuantityPassenger();
        }
}
