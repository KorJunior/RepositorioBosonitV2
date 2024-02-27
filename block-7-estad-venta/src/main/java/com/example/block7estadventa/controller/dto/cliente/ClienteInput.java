package com.example.block7estadventa.controller.dto.cliente;

import lombok.*;
import org.example.dto.dtoCliente.clienteOutput.ClienteOutPutHistorico;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteInput {
    private String dni;
    private String nombre;

    public ClienteInput(ClienteOutPutHistorico clienteObtenido) {
        this.dni = clienteObtenido.getDni();
        this.nombre = clienteObtenido.getNombre();
    }
}
