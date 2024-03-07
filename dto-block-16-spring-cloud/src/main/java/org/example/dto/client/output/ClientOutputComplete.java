package org.example.dto.client.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dto.trip.output.TripOutputSimple;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientOutputComplete {
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private int telefono;

    private TripOutputSimple tripId;
}
