package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dtoInterno.producto.output;

import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Producto;
import org.example.dto.dtoProducto.productoOutPutDto.ProductoOutPutHistorico;

public class ProductoOutPutHistoricoEntity extends ProductoOutPutHistorico {



    public ProductoOutPutHistoricoEntity(Producto producto) {
        this.setId(producto.getIdProducto());
        this.setNombre(producto.getDescripcionProducto());
    }
}
