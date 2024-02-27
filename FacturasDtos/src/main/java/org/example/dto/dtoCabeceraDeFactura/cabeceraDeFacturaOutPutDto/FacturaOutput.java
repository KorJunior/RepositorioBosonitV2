package org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto;


import lombok.*;
import org.example.dto.dtoCliente.clienteOutput.ClienteOutPutDtoSimple;
import org.example.dto.dtoLineasDeFactura.lineasDeFacturaOutPut.LineasDeFacturaOutPutFactura;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacturaOutput {
    private int numero_factura;
    private Date fecha;
    private ClienteOutPutDtoSimple cliente;
    private List<LineasDeFacturaOutPutFactura> lineas;
    private float totalFactura;



}
