package org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.dtoCliente.clienteOutput.ClienteOutPutDtoSimple;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CabeceraDeFacturaOutPutDtoSimple {
    private Date fechaFactura;
    private float importeTotal;
    private ClienteOutPutDtoSimple cliente;
}
