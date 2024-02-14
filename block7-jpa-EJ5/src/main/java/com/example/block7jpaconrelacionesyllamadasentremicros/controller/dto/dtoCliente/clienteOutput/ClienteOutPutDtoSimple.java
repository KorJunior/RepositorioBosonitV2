package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCliente.clienteOutput;

import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProvincia.provinciaOutPut.ProvinciaOutPutSimple;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteOutPutDtoSimple {
    private String nombre;
    private ProvinciaOutPutSimple provincia;

    public ClienteOutPutDtoSimple(Cliente cliente) {
        this.nombre = cliente.getNombre();
        this.provincia = cliente.getProvincia().toProvinciaOutPutSimple();
    }

}
