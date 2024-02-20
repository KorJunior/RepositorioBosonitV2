package org.example.dto.dtoLineasDeFactura.lineasDeFacturaOutPut;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.CabeceraDeFacturaOutPutDtoSimple;
import org.example.dto.dtoProducto.productoOutPutDto.ProductoOutPutDtoSimple;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineasDeFacturaOutPutComplete {

    private int idLinea;
    private int cantidad;
    private float precio;
    private float importe;

    private ProductoOutPutDtoSimple producto;

    private CabeceraDeFacturaOutPutDtoSimple cabeceraDeFactura;
}
