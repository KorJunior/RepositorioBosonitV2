package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProducto.productoInputDto;

import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoLineasDeFactura.lineasDeFacturaOutPut.LineasDeFacturaOutPutComplete;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoInputDto {

    private Long idProducto;
    private String descripcionProducto;
    private float precioProducto;

    private LineasDeFacturaOutPutComplete lineasDeFacturas;


}
