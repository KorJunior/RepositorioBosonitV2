package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dtoInterno.cabeceraDeFactura.output;

import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dtoInterno.cliente.output.ClienteOutPutDtoSimpleEntity;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dtoInterno.lineasFactura.output.LineasDeFacturaOutPutFacturaEntity;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.CabeceraDeFactura;
import org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.FacturaOutput;
import org.example.dto.dtoCliente.clienteOutput.ClienteOutPutDtoSimple;

import java.util.stream.Collectors;

public class FacturaOutputEntity extends FacturaOutput {


    public FacturaOutputEntity(CabeceraDeFactura facturaBase) {
        this.setNumero_factura(facturaBase.getIdFactura());
        this.setFecha(facturaBase.getFechaFactura());
        this.setCliente(new ClienteOutPutDtoSimpleEntity(facturaBase.getCliente()));
        this.setLineas(facturaBase.getLineasDeFactura().stream()
                .map(LineasDeFacturaOutPutFacturaEntity::new)
                .collect(Collectors.toList()));
        this.setTotalFactura(facturaBase.getImporteTotal());
    }
}
