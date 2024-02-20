package org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dto.dtoProducto.productoOutPutDto.ProductoCantidadOutPut;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacturaOutPutHistorico {
    private int numero_factura;
    private Date fecha;
    private String cliente;
    private List<ProductoCantidadOutPut> productos;
    private float totalFactura;

//    public FacturaOutPutHistorico(CabeceraDeFactura cabeceraDeFactura) {
//        this.numero_factura = cabeceraDeFactura.getIdFactura();
//        this.fecha = cabeceraDeFactura.getFechaFactura();
//        this.cliente = cabeceraDeFactura.getCliente().getDni();
//        this.productos = cabeceraDeFactura.getLineasDeFactura().stream()
//                .map(linea -> new ProductoCantidadOutPut(linea.getProducto().getIdProducto(), linea.getCantidad(), linea.getIdLinea()))
//                .collect(Collectors.toList());
//        this.totalFactura = cabeceraDeFactura.getImporteTotal();
//    }
}


