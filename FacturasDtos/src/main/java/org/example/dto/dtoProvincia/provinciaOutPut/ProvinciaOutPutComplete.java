package org.example.dto.dtoProvincia.provinciaOutPut;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.dtoCliente.clienteOutput.ClienteOutPutDtoSimple;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinciaOutPutComplete {

    private int provincia;
    private String nombre;
    private List<ClienteOutPutDtoSimple> clientes;
}
