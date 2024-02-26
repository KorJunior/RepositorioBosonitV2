package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCliente.clienteOutput;

import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ClienteOutPutHistorico {
    private String dni;
    private String nombre;

    public ClienteOutPutHistorico(Cliente cliente) {
        this.dni = cliente.getDni();
        this.nombre = cliente.getNombre();
    }
}
