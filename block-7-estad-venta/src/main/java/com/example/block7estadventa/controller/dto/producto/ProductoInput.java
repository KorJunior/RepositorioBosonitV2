package com.example.block7estadventa.controller.dto.producto;

import lombok.*;
import org.example.dto.dtoProducto.productoOutPutDto.ProductoOutPutHistorico;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoInput {
    private long id;
    private String nombre;


    public ProductoInput(ProductoOutPutHistorico productoObtenido) {
        this.id = productoObtenido.getId();
        this.nombre = productoObtenido.getNombre();
    }
}
