package org.example.dto.dtoCliente.clienteOutput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.CabeceraDeFacturaOutPutDtoSimple;
import org.example.dto.dtoProvincia.provinciaOutPut.ProvinciaOutPutSimple;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteOutputDtoComplete {
    private String dni;
    private String nombre;
    private String direccion;
    private ProvinciaOutPutSimple codProvincia;
    private List<CabeceraDeFacturaOutPutDtoSimple> cabeceraDeFacturas;




}
