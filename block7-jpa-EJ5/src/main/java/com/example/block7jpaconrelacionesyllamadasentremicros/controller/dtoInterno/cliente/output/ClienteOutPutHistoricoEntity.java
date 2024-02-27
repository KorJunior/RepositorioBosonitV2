package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dtoInterno.cliente.output;

import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Cliente;
import org.example.dto.dtoCliente.clienteOutput.ClienteOutPutHistorico;

public class ClienteOutPutHistoricoEntity extends ClienteOutPutHistorico {

    public ClienteOutPutHistoricoEntity(Cliente cliente) {
        this.setDni(cliente.getDni());
        this.setNombre(cliente.getNombre());
    }
}
