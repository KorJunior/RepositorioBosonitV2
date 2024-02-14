package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto;

import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCliente.clienteOutput.ClienteOutPutDtoSimple;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoLineasDeFactura.lineasDeFacturaOutPut.LineasDeFacturaOutPutComplete;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoLineasDeFactura.lineasDeFacturaOutPut.LineasDeFacturaOutPutSimple;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CabeceraDeFacturaOutPutDtoComplete {

    private int idFactura;
    private Date fechaFactura;
    private float importeTotal;
    private ClienteOutPutDtoSimple cliente;
    private List<LineasDeFacturaOutPutSimple> lineasFactura;


    public CabeceraDeFacturaOutPutDtoComplete(int idFactura, Date fechaFactura, float importeTotal, ClienteOutPutDtoSimple clienteOutputDtoProvincia) {
        this.idFactura = idFactura;
        this.fechaFactura = fechaFactura;
        this.importeTotal = importeTotal;
        this.cliente = clienteOutputDtoProvincia;
    }
}
