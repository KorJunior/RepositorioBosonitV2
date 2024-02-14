package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto;

import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCliente.clienteOutput.ClienteOutPutDtoSimple;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CabeceraDeFacturaOutPutDtoSimple {
    private Date fechaFactura;
    private float importeTotal;
    private ClienteOutPutDtoSimple cliente;
}
