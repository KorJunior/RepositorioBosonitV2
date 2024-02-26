package com.example.block7estadventa.domain;

import com.example.block7estadventa.controller.dto.cliente.ClienteInput;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Cliente {
    @Id
    private String dni;
    private String nombre;

    public Cliente(ClienteInput cliente) {
        this.dni = cliente.getDni();
        this.nombre = cliente.getNombre();
    }
}
