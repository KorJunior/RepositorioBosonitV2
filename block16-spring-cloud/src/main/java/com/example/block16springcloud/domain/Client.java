package com.example.block16springcloud.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dto.client.ClientInput;
import org.example.dto.client.output.ClientOutPutSimple;
import org.example.dto.client.output.ClientOutputComplete;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue
    private int clientId;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private int telefono;
    @ManyToOne
    @JoinColumn(name = "tripId")
    private Trip trip;

    public Client(ClientInput client) {
        this.nombre = client.getNombre();
        this.apellido = client.getApellido();
        this.edad = client.getEdad();
        this.email = client.getEmail();
        this.telefono = client.getTelefono();
    }

    public ClientOutPutSimple toClientOutPutSimple() {
        return new ClientOutPutSimple(this.nombre, this.apellido, this.edad, this.email, this.telefono);
    }

    public ClientOutputComplete toClientOutPutComplete() {
        return new ClientOutputComplete(this.clientId, this.nombre, this.apellido, this.edad, this.email, this.telefono, this.trip != null ? this.trip.toTripOutputSimple() : null);
    }
}
