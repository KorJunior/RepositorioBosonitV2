package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dtoInterno.cabeceraDeFactura.output;

import com.example.block7jpaconrelacionesyllamadasentremicros.domain.CabeceraDeFactura;
import org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.FacturaOutPutHistorico;
import org.example.dto.dtoProducto.productoOutPutDto.ProductoCantidadOutPut;

import java.util.stream.Collectors;

public class FacturaOutPutHistoricoEntity  extends FacturaOutPutHistorico {

    public FacturaOutPutHistoricoEntity(CabeceraDeFactura cabeceraDeFactura) {
        this.setNumero_factura(cabeceraDeFactura.getIdFactura());
        this.setFecha(cabeceraDeFactura.getFechaFactura());
        this.setCliente(cabeceraDeFactura.getCliente().getDni());
        this.setProductos(cabeceraDeFactura.getLineasDeFactura().stream()
                .map(linea -> new ProductoCantidadOutPut(linea.getProducto().getIdProducto(), linea.getCantidad(), linea.getIdLinea()))
                .collect(Collectors.toList()));
        this.setTotalFactura(cabeceraDeFactura.getImporteTotal());
    }
}
