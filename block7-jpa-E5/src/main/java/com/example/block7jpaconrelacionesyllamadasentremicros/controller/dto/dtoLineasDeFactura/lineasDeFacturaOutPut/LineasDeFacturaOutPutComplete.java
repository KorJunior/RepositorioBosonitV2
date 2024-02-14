package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoLineasDeFactura.lineasDeFacturaOutPut;

import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.CabeceraDeFacturaOutPutDtoComplete;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoCabeceraDeFactura.cabeceraDeFacturaOutPutDto.CabeceraDeFacturaOutPutDtoSimple;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProducto.productoOutPutDto.ProductoOutPutDtoSimple;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
