package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoLineasDeFactura.lineasDeFacturaOutPut;

import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProducto.productoOutPutDto.ProductoOutPutDtoSimple;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProducto.productoOutPutDto.ProductoOutPutSimpleFactura;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.LineasDeFactura;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineasDeFacturaOutPutFactura {
    private ProductoOutPutSimpleFactura producto;
    private int cantidad;
    private float precio;

    public LineasDeFacturaOutPutFactura(LineasDeFactura lineasDeFactura) {
        this.producto = new ProductoOutPutSimpleFactura(lineasDeFactura.getProducto());
        this.cantidad = lineasDeFactura.getCantidad();
        this.precio = lineasDeFactura.getPrecio();

    }
}
