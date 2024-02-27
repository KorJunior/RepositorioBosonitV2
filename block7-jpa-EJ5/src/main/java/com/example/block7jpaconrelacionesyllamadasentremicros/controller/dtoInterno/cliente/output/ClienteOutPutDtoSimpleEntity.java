package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dtoInterno.cliente.output;

import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Cliente;
import org.example.dto.dtoCliente.clienteOutput.ClienteOutPutDtoSimple;


public class ClienteOutPutDtoSimpleEntity extends ClienteOutPutDtoSimple {

    public ClienteOutPutDtoSimpleEntity(Cliente cliente) {
        this.setNombre(cliente.getNombre());
        this.setProvincia(cliente.getProvincia().toProvinciaOutPutSimple());
    }
}
