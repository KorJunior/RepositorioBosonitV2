package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProducto.productoOutPutDto;

import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Producto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoOutPutHistorico {
    private long id;
    private String nombre;

    public ProductoOutPutHistorico(Producto producto) {
        this.id = producto.getIdProducto();
        this.nombre = producto.getDescripcionProducto();
    }
}
