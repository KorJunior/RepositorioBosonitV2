package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto;

import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCliente.clienteOutput.ClienteOutPutDtoSimple;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoLineasDeFactura.lineasDeFacturaOutPut.LineasDeFacturaOutPutFactura;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.CabeceraDeFactura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaOutput {
    private int numero_factura;
    private Date fecha;
    private ClienteOutPutDtoSimple cliente;
    private List<LineasDeFacturaOutPutFactura> lineas;
    private float totalFactura;


    public FacturaOutput(CabeceraDeFactura facturaBase) {
        this.numero_factura = facturaBase.getIdFactura();
        this.fecha = facturaBase.getFechaFactura();
        this.cliente = new ClienteOutPutDtoSimple(facturaBase.getCliente());
        this.lineas = facturaBase.getLineasDeFactura().stream().map(LineasDeFacturaOutPutFactura::new).toList();
        this.totalFactura = facturaBase.getImporteTotal();
    }
}
